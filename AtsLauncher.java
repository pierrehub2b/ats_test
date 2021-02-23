import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AtsLauncher {

	private static String atsToolsUrl = "http://www.actiontestscript.com";

	private static void printLog(String data) {
		System.out.println("[ATS-LAUNCHER] " + data);
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		boolean buildOnly = false;
		String suiteFiles = "suite";
		String reportLevel = "1";

		for(int i=0; i<args.length; i++) {
			if(args[i].startsWith("-buildEnvironment")) {
				buildOnly = true;
				break;
			}else {
				if(args[i].startsWith("-suiteXmlFiles=")) {
					suiteFiles = args[i].substring(15);
				}else if(args[i].startsWith("-ats-report=")) {
					reportLevel = args[i].substring(12);
				}else if(args[i].startsWith("-toolsUrl=")) {
					atsToolsUrl = args[i].substring(10);
				}
			}
		}		

		deleteDirectory(Paths.get("target"));
		deleteDirectory(Paths.get("test-output"));

		final Path atsFolder = Paths.get(System.getProperty("user.home"), ".actiontestscript");

		final Path atsTools = atsFolder.resolve("tools");
		printLog("Using tools folder : " + atsTools.toString());

		List<String> envList = new ArrayList<String>();

		createEnVar(envList, "jasper", atsTools);

		String[] env = createEnVar(envList, "ats", atsTools);
		String atsHomePath = Paths.get(env[1]).toAbsolutePath().toString();

		env = createEnVar(envList, "jdk", atsTools);
		String jdkHomePath = Paths.get(env[1]).toAbsolutePath().toString();

		if(buildOnly) {
			Path buildProperties = Paths.get("build.properties");
			Files.deleteIfExists(buildProperties);
			Files.write(buildProperties, String.join("\n", envList).getBytes(), StandardOpenOption.CREATE);
		}else {

			Map<String, String> userEnv = System.getenv();
			for (String envName : userEnv.keySet()) {
				envList.add(0, envName + "=" + userEnv.get(envName));
			}

			File currentDirectory = Paths.get("").toAbsolutePath().toFile();
			printLog("Current directory : " + currentDirectory.getAbsolutePath());
			String[] envArray = envList.toArray(new String[envList.size()]);

			printLog("Generate java files : " + Paths.get("target", "generated").toString());
			execute(
					"\"" + jdkHomePath + "/bin/java.exe\" -cp " + atsHomePath + "/libs/* com.ats.generator.Generator -prj \"" + currentDirectory.getAbsolutePath() + "\" -dest target/generated -force -suites " + suiteFiles, 
					envArray, 
					currentDirectory);

			File sourceDir = Paths.get("target", "generated").toFile();
			StringJoiner files = new StringJoiner("\n");
			listJavaClasses(files, sourceDir.getAbsolutePath().length()+1, sourceDir);

			Path classFolder = Paths.get("target", "classes").toAbsolutePath();
			Path classFolderAssets = classFolder.resolve("assets");
			classFolderAssets.toFile().mkdirs();

			copyFolder(Paths.get("src", "assets"), classFolderAssets);

			printLog("Compile classes : " + Paths.get("target", "classes").toString());
			Files.write(Paths.get("target", "generated", "JavaClasses.list"), files.toString().getBytes(), StandardOpenOption.CREATE);
			execute("\"" + jdkHomePath + "/bin/javac.exe\" -cp " + atsHomePath + "/libs/* -d \"" + classFolder.toString() + "\" @JavaClasses.list", 
					envArray, 
					Paths.get("target", "generated").toAbsolutePath().toFile());

			printLog("Launch suites execution : " + suiteFiles);
			execute("\"" + jdkHomePath + "/bin/java.exe\" -Doutput-folder=target/ats-output -Dats-report=" + reportLevel + " -cp " + atsHomePath + "/libs/*" + File.pathSeparator + "target/classes" + File.pathSeparator + "libs/* org.testng.TestNG target/suites.xml", 
					envArray, 
					currentDirectory);
		}
	}

	private static void copyFolder(Path src, Path dest) throws IOException {
		try (Stream<Path> stream = Files.walk(src)) {
			stream.forEach(source -> copy(source, dest.resolve(src.relativize(source))));
		}
	}

	private static void copy(Path source, Path dest) {
		try {
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static void listJavaClasses(StringJoiner list, int subLen, File directory) {
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				if(file.getName().endsWith(".java")) {
					list.add(file.getAbsolutePath().substring(subLen).replaceAll("\\\\", "/"));
				}
			} else if (file.isDirectory()) {
				listJavaClasses(list, subLen, file);
			}
		}
	}

	private static void execute(String commandLine, String[] envp, File currentDir) throws IOException, InterruptedException {
		final Process p = Runtime.getRuntime().exec(commandLine, envp, currentDir);
		new Thread(new Runnable() {
			public void run() {
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				try {
					while ((line = input.readLine()) != null)
						System.out.println(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		p.waitFor();
	}

	private static String[] createEnVar(List<String> envList, String toolName, Path atsTools) throws IOException {
		final String toolPath = installTool(atsTools, toolName);
		final String envName = toolName.toUpperCase() + "_HOME";

		printLog("Set environment variable [" + envName + "] to " + toolPath);

		envList.add(envName + "=" + toolPath);

		return new String[]{envName, toolPath};
	}

	private static String installTool(Path atsTools, String toolName) throws IOException {

		final URLConnection connection = new URL(atsToolsUrl + "/tools/" + toolName + ".zip").openConnection();
		final String lastModified = connection.getHeaderField("Last-Modified");

		final String toolPath = checkTool(lastModified, atsTools, toolName);
		if(toolPath != null) {
			return toolPath;
		}

		printLog("Unpacking tool [" + toolName + "] to folder : " + atsTools.toString());

		final Path tmpZip = Files.createTempDirectory("atsTool_").resolve(toolName + ".zip");
		final InputStream in = new URL(atsToolsUrl + "/tools/" + toolName + ".zip").openStream();
		Files.copy(in, tmpZip, StandardCopyOption.REPLACE_EXISTING);

		String newToolPath = null;

		byte[] buffer = new byte[1024];

		final ZipInputStream zis = new ZipInputStream(new FileInputStream(tmpZip.toFile()));
		ZipEntry zipEntry = zis.getNextEntry();

		while (zipEntry != null) {
			final File newFile = newFile(atsTools.toFile(), zipEntry);
			if(newFile != null && !newFile.exists()) {
				if (zipEntry.isDirectory()) {

					if (!newFile.isDirectory() && !newFile.mkdirs()) {
						throw new IOException("Failed to create directory " + newFile);
					}

					if(newToolPath == null) {
						Files.write(atsTools.resolve(zipEntry.getName()).resolve(".timestamp"), lastModified.getBytes(), StandardOpenOption.CREATE_NEW);
						newToolPath = newFile.getAbsolutePath();
					}

				} else {

					final File parent = newFile.getParentFile();
					if (!parent.isDirectory() && !parent.mkdirs()) {
						throw new IOException("Failed to create directory " + parent);
					}

					final FileOutputStream fos = new FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
				}
			}
			zipEntry = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();

		return newToolPath;
	}

	private static String checkTool(String lastModified, Path atsTools, String toolName) throws IOException {
		final File[] fs = atsTools.toFile().listFiles((dir, name) -> name.startsWith(toolName + "-"));
		if(fs != null) {
			for(File f : fs) {
				if(f.isDirectory()) {
					final Path ts = f.toPath().resolve(".timestamp");
					if(Files.exists(ts) && lastModified.equals(Files.readString(ts, StandardCharsets.UTF_8))) {
						return f.getAbsolutePath();
					}
					deleteDirectory(f.toPath());
				}
			}
		}
		return null;
	}

	private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		final File destFile = new File(destinationDir, zipEntry.getName());
		if (destFile.getCanonicalPath().startsWith(destinationDir.getCanonicalPath() + File.separator)) {
			return destFile;
		}
		return null;
	}

	private static void deleteDirectory(Path directory) throws IOException
	{
		if (Files.exists(directory))
		{
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException
				{
					Files.delete(path);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException
				{
					Files.delete(directory);
					return FileVisitResult.CONTINUE;
				}
			});
		}
	}
}