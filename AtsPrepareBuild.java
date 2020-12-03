import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AtsPrepareBuild {

	private static final String atsToolsUrl = "http://www.actiontestscript.com";

	private static void printLog(String data) {
		System.out.println("[Ats-Tools] " + data);
	}

	public static void main(String[] args) throws IOException {

		deleteDirectory(Paths.get("target"));
		deleteDirectory(Paths.get("test-output"));

		final Path atsTools = Paths.get(System.getProperty("user.home"),".actiontestscript", "tools");
		printLog("Using tools folder : " + atsTools.toString());

		final StringJoiner sj = new StringJoiner("\n");

		createEnVar(sj, "jasper", atsTools);
		createEnVar(sj, "ats", atsTools);
		createEnVar(sj, "jdk", atsTools);

		Files.write(Paths.get("build.properties"), sj.toString().getBytes(), StandardOpenOption.CREATE);
	}

	private static void createEnVar(StringJoiner sj, String toolName, Path atsTools) throws IOException {
		final String toolPath = installTool(atsTools, toolName);
		final String envName = toolName.toUpperCase() + "_HOME";

		printLog("Set environment variable [" + envName + "] to " + toolPath);

		sj.add(envName + "=" + toolPath);
		if("jdk".equals(toolName)) {
			sj.add("JAVA_HOME=" + toolPath);
		}
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

	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		final File destFile = new File(destinationDir, zipEntry.getName());
		if (destFile.getCanonicalPath().startsWith(destinationDir.getCanonicalPath() + File.separator)) {
			return destFile;
		}
		return null;
	}

	public static void deleteDirectory(Path directory) throws IOException
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