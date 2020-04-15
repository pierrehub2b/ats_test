import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;
import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

public class Javatest extends ActionTestScript{

	/**
	 * Test Name : <b>Javatest</b>
	 * Test Author : <b>AGILITEST-DUPER\Emmanuel DUPERRAY</b>
	 * Test Description : <i></i>
	 * Test Prerequisites : <i></i>
	 */

	@Test
	public void testMain(){
		// -----------------------------------------------
		// Get parameters passed by the calling script :
		// getParameter(int index)
		// -----------------------------------------------
		// String param0 = getParameter(0).toString();
		// int param0 = getParameter(0).toInt();
		// double param0 = getParameter(0).toDouble();
		// boolean param0 = getParameter(0).toBoolean();
		// -----------------------------------------------
		// int it = getIteration(); -> return current iteration loop
		// String path = getCsvFilePath(); -> return csv file path sent as parameter to call current script
		// File file = getCsvFile(); -> return csv file sent as parameter to call current script
		// File file = getAssetsFile("[relative path string]"); -> return a file in the project's 'assets' folder
		// String url = getAssetsUrl("[relative path string]"); -> return url path of a file in the project's 'assets' folder
		
		
String param0 = getParameter(0).toString();
int param1 = getParameter(1).toInt();
double param2 = getParameter(2).toDouble();
boolean param3 = getParameter(3).toBoolean();


System.out.println("it -> " + getIteration());
System.out.println("path -> " + getCsvFilePath());

System.out.println("param string by index -> " + getParameter(4).toString());
System.out.println("param string by name -> " + getParameter("result").toString());

//returnValues(param0 ,param1, param2, param3, "value");
		
		
		// -----------------------------------------------
		// Return string values to calling script :
		// returnValues(String ...)
		// -----------------------------------------------
		// returnValues("value", stringVariable);
	}
}