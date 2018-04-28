package subscripts.dragdrop;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;

import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

public class SwipeList extends ActionTestScript{

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
	* Test Name : <b>SwipeList</b>
	* Test Author : <b>pc_pierre\huber</b>
	* Test Description : <i></i>
	* Test Prerequisites : <i></i>
	* Generated at : <b>28 avr. 2018 à 09:57:59</b>
	*/

	@Test
	public void testMain(){

		//------------------------------------------------------------------------
		// Variables
		//------------------------------------------------------------------------


		//------------------------------------------------------------------------
		// Actions
		//------------------------------------------------------------------------

		exec(0,new ActionChannelStart(this, "1", cv(pm(0, "chrome")), null));
		exec(1,new ActionGotoUrl(this, true, cv("http://examples.sencha.com/extjs/6.5.1/examples/kitchensink/?modern#basic-accordion-swiper")));
		exec(2,new ActionMouseScroll(this, true, 30, el(el(el(0, "DIV", pf(false, "class", cv("x-list x-dataview x-container x-component x-infinite x-heighted x-widthed x-shadow x-layout-center-item"))), 0, "DIV", pf(false, "class", cv("x-body-el x-list-body-el x-dataview-body-el x-container-body-el x-component-body-el x-size-monitored x-paint-monitored"))), 0, "DIV", pf(false, "class", cv("x-list-outer-ct x-scroller"))), ms(200)));
		exec(3,new ActionMouse(this, true, -3, el(0, "DIV", pf(false, "class", cv("x-inner-el x-listitem-inner-el x-component-inner-el")), pf(false, "text", cv("Allan Brummer"))), ms(Mouse.OVER)));
		exec(4,new ActionMouseKey(this, true, 0, el(0, "DIV", pf(true, "class", cv("x-inner-el x-listitem-inner-el x-component-inner-el.*?")), pf(false, "text", cv("Ashlee Ricco"))), ms(Mouse.CLICK)));
		exec(5,new ActionMouseKey(this, true, 0, el(0, "DIV", pf(true, "class", cv("x-inner-el x-listitem-inner-el x-component-inner-el.*?")), pf(false, "text", cv("Allan Ricca"))), ms(Mouse.CLICK)));
		exec(6,new ActionMouseSwipe(this, true, 0, el(0, "DIV", pf(true, "class", cv("x-inner-el x-listitem-inner-el x-component-inner-el.*?")), pf(false, "text", cv("Allan Ricca"))), ms(-60, 0)));
		exec(7,new ActionMouseKey(this, true, 0, el(0, "DIV", pf(true, "class", cv("x-body-el x-listitem-body-el x-component-body-el.*?")), pf(true, "text", cv("(?i).*MESSAGE.*GEAR.*Allan Ricca"))), ms(Mouse.CLICK)));
		exec(8,new ActionText(this, true, 0, el(0, "INPUT", pf(false, "class", cv("x-input-el"))), cv("Hello")));
		exec(9,new ActionMouseKey(this, true, 0, el(el(el(0, "DIV", pf(false, "class", cv("x-body-el x-toolbar-body-el x-container-body-el x-component-body-el x-layout-box x-layout-hbox x-horizontal x-pack-end x-align-center x-toolbar-footer-body-el x-container-footer-body-el x-component-footer-body-el"))), 0, "DIV", pf(false, "class", cv("x-component x-button x-has-text x-icon-align-left x-arrow-align-right x-layout-box-item x-layout-hbox-item")), pf(false, "text", cv("OK"))), 0, "BUTTON", pf(false, "class", cv("x-button-el"))), ms(Mouse.CLICK)));
		exec(10,new ActionMouseScroll(this, true, 0, el(0, "DIV", pf(true, "class", cv("x-inner-el x-listitem-inner-el x-component-inner-el.*?")), pf(false, "text", cv("Allan Brummer"))), ms(0)));
		exec(11,new ActionMouseScroll(this, true, 0, el(el(el(0, "DIV", pf(false, "class", cv("x-list x-dataview x-container x-component x-infinite x-heighted x-widthed x-shadow x-layout-center-item"))), 0, "DIV", pf(false, "class", cv("x-body-el x-list-body-el x-dataview-body-el x-container-body-el x-component-body-el x-size-monitored x-paint-monitored"))), 0, "DIV", pf(false, "class", cv("x-list-outer-ct x-scroller"))), ms(400)));
		exec(12,new ActionMouseScroll(this, true, 0, el(el(el(0, "DIV", pf(false, "class", cv("x-list x-dataview x-container x-component x-infinite x-heighted x-widthed x-shadow x-layout-center-item"))), 0, "DIV", pf(false, "class", cv("x-body-el x-list-body-el x-dataview-body-el x-container-body-el x-component-body-el x-size-monitored x-paint-monitored"))), 0, "DIV", pf(false, "class", cv("x-list-outer-ct x-scroller"))), ms(-200)));
		exec(13,new ActionMouseKey(this, true, 0, el(0, "DIV", pf(true, "class", cv("x-inner-el x-listitem-inner-el x-component-inner-el.*?")), pf(false, "text", cv("Allan Brummer"))), ms(Mouse.CLICK)));
		exec(14,new ActionChannelClose(this, "1"));
	}
}