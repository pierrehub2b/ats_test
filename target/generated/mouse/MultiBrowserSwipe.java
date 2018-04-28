package mouse;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;

import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

public class MultiBrowserSwipe extends ActionTestScript{

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
	* Test Name : <b>MultiBrowserSwipe</b>
	* Test Author : <b>pc_pierre\huber</b>
	* Test Description : <i></i>
	* Test Prerequisites : <i></i>
	* Generated at : <b>28 avr. 2018 à 09:57:59</b>
	*/

	@Test(groups={"default"})
	public void testMain(){

		//------------------------------------------------------------------------
		// Variables
		//------------------------------------------------------------------------


		//------------------------------------------------------------------------
		// Actions
		//------------------------------------------------------------------------

		exec(0,new ActionCallscript(this, "subscripts.dragdrop.SwipeList", "assets:///data/browsers.csv"));

		rt(cv(""));
	}
}