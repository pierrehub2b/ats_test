import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;
import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

public class Wait extends ActionTestScript{

	/**
	 * Test Name : <b>Wait</b>
	 * Test Author : <b>pc_pierre\huber</b>
	 * Test Description : <i></i>
	 * Test Prerequisites : <i></i>
	 */

	@Test
	public void testMain(){
		try {
			final int ms = Integer.parseInt(prm(0, "1000"));
			Thread.sleep(ms);
		} catch (Exception e) {}
	}
}