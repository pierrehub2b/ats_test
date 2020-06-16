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

public class lineProcessor extends ActionTestScript{

	/**
	 * Test Name : <b>lineProcessor</b>
	 * Test Author : <b>ALFRGIS\ayoub.idaras</b>
	 * Test Description : Insère une ligne de la format "cell1,cell2,cell3,...,cellx" dans un fichier CSV <i></i>
	 * Test Prerequisites : Ce script utilise 3 paramètres en entrée <i></i>
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
                     String tmpVal = getParameter(1).toString();//param1 correspond à la ligne de format "cell1,cell2,cell3,...,cellx" que l'on veut insérer dans le CSV
                     tmpVal = tmpVal.replaceAll("(\t|\r?\n)+", " ");
                     int coordY = getParameter(2).toInt();//param2 correspond au numéro de la ligne à laquelle on veut insérer tmpVal (écrasera toute donnée se trouvant sur cette ligne si déjà existante)
  
                     csv.open(file); // doit précéder un get 
            
                     csv.addLine(tmpVal, ",", coordY);
            
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