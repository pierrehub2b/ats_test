package csvProcessor;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import com.ats.executor.ActionTestScript;
import com.ats.script.actions.*;
import com.ats.generator.objects.Cartesian;
import com.ats.generator.objects.mouse.Mouse;
import com.ats.tools.Operators;
import com.ats.generator.variables.Variable;

import java.io.File;

public class iterationTest extends ActionTestScript{

	/**
	 * Test Name : <b>iterationTest</b>
	 * Test Author : <b>ALFRGIS\ayoub.idaras</b>
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
		
		try {
                     CSV csv = new CSV();
                     String filePath = getParameter(0).toString();//param0 correspond au chemin absolu menant au fichier CSV (à modifier d'un utilisateur à un autre)
                     File file = new File(filePath);
                     int coordY = getIteration();
                     int coordX = 1;//param2 correspond au numéro de la colonne à laquelle on veut insérer tmpVal (écrasera toute donnée se trouvant sur cette ligne si déjà existante)
                     
                     
                       
                     csv.open(file); // doit précéder un get 
                     String tmpVal =Integer.toString(coordY);
            
                     csv.put(coordX, coordY, tmpVal);
            
                     csv.save(file); // un quelconque put doit précéder le save
               } 
               catch (Exception e) {
               }	
		
		
		
		
		// -----------------------------------------------
		// Return string values to calling script :
		// returnValues(String ...)
		// -----------------------------------------------
		// returnValues("value", stringVariable);
	}
}