package subscripts;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;

import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

public class DemoCalculatrice extends ActionTestScript{

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
	* Test Name : <b>DemoCalculatrice</b>
	* Test Author : <b>AGILITEST-DUPER\Emmanuel DUPERRAY</b>
	* Test Description : <i>Tests sur calculatrice google:<br>- simple clic<br>- récupération de propriété "résultat"<br>- comparaison au résultat attendu</i>
	* Test Prerequisites : <i></i>
	* Generated at : <b>28 avr. 2018 à 09:57:59</b>
	*/

	@Test
	public void testMain(){

		//------------------------------------------------------------------------
		// Variables
		//------------------------------------------------------------------------

		Variable Resultat_Calculatrice = va("Resultat_Calculatrice");

		//------------------------------------------------------------------------
		// Actions
		//------------------------------------------------------------------------

		exec(0,new ActionChannelStart(this, "Canal Google", cv(pm(0, "chrome")), null));
		exec(2,new ActionGotoUrl(this, true, cv("www.google.fr")));
		exec(4,new ActionText(this, true, 0, el(0, "INPUT", pf(false, "class", cv("gsfi lst-d-f")), pf(false, "id", cv("lst-ib"))), cv("calculatrice", Keys.ENTER)));
		exec(6,new ActionMouseKey(this, true, 0, el(el(0, "DIV", pf(false, "class", cv("cwbtpl cwlgb-tpl")), pf(false, "text", cv("8"))), 0, "SPAN", pf(false, "class", cv("cwbts"))), ms(Mouse.CLICK)));
		exec(8,new ActionMouseKey(this, true, 0, el(el(0, "DIV", pf(false, "class", cv("cwbtpl cwdgb-tpl cwbbts")), pf(false, "text", cv("+"))), 0, "SPAN", pf(false, "class", cv("cwbts"))), ms(Mouse.CLICK)));
		exec(10,new ActionMouseKey(this, true, 0, el(el(0, "DIV", pf(false, "class", cv("cwbtpl cwlgb-tpl")), pf(false, "text", cv("8"))), 0, "SPAN", pf(false, "class", cv("cwbts"))), ms(Mouse.CLICK)));
		exec(12,new ActionMouseKey(this, true, 0, el(el(0, "DIV", pf(false, "class", cv("cwbtpl cwbb-tpl cwbbts"))), 0, "SPAN", pf(false, "class", cv("cwbts"))), ms(Mouse.CLICK)));
		exec(14,new ActionProperty(this, true, 0, el(0, "SPAN", pf(false, "class", cv("cwcot")), pf(false, "id", cv("cwos"))), "innerHTML", Resultat_Calculatrice));
		exec(16,new ActionAssertValue(this, true, false, cv(Resultat_Calculatrice), cv("16")));
		exec(18,new ActionChannelClose(this, "Canal Google"));
	}
}