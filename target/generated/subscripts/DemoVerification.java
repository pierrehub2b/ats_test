package subscripts;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;

import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

public class DemoVerification extends ActionTestScript{

	//------------------------------------------------------------------------
	//     _  _____ ____     ____                           _             
	//    / \|_   _/ ___|   / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __ 
	//   / _ \ | | \___ \  | |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
	//  / ___ \| |  ___) | | |_| |  __/ | | |  __/ | | (_| | || (_) | |   
	// /_/   \_\_| |____/   \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|   
	//
	//------------------------------------------------------------------------
	//	!! Warning !!
	//	Class automatically generated by ATS Script Generator (ver. 1.2.1)
	//	You may loose modifications if you edit this file manually !
	//------------------------------------------------------------------------

	/**
	* Test Name : <b>DemoVerification</b>
	* Test Author : <b>AGILITEST-DUPER\Emmanuel DUPERRAY</b>
	* Test Description : <i></i>
	* Test Prerequisites : <i></i>
	* Generated at : <b>28 avr. 2018 à 09:57:59</b>
	*/

	@Test
	public void testMain(){

		//------------------------------------------------------------------------
		// Variables
		//------------------------------------------------------------------------

		Variable valeur_recuperee = va("valeur_recuperee");

		//------------------------------------------------------------------------
		// Actions
		//------------------------------------------------------------------------

		exec(0,new ActionChannelStart(this, "Verification", cv(pm(0, "chrome")), null));
		exec(1,new ActionGotoUrl(this, true, cv("http://examples.sencha.com/extjs/6.5.1/examples/kitchensink/?modern#binding-selection")));
		exec(2,new ActionMouseKey(this, true, 20, el(0, "DIV", pf(false, "id", cv("ext-element-120"))), ms(Mouse.CLICK)));
		exec(3,new ActionText(this, true, 0, el(0, "INPUT", pf(false, "id", cv("ext-element-70"))), cv("Pr\u00E9nom_1")));
		exec(4,new ActionText(this, true, 0, el(0, "INPUT", pf(false, "id", cv("ext-element-83"))), cv("Nom_1")));
		exec(5,new ActionAssertProperty(this, true, 0, el(0, "DIV", pf(false, "id", cv("ext-element-120"))), pf(false, "text", cv("Nom_1, Pr\u00E9nom_1"))));
		exec(6,new ActionMouseKey(this, true, 0, el(0, "DIV", pf(false, "id", cv("ext-element-126"))), ms(Mouse.CLICK)));
		exec(7,new ActionProperty(this, true, 0, el(0, "DIV", pf(false, "id", cv("ext-element-120"))), "text", valeur_recuperee));
		exec(8,new ActionAssertValue(this, true, true, cv(valeur_recuperee), cv("Nom_1,.Pr\u00E9nom_1")));
		exec(9,new ActionChannelClose(this, "Verification"));
	}
}