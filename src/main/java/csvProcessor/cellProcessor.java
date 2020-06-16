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

public class cellProcessor extends ActionTestScript{

	/**
	 * Test Name : <b>cellProcessor</b>
	 * Test Author : <b>ALFRGIS\ayoub.idaras</b>
	 * Test Description : Insère une cellule dans le fichier CSV à des coordonnées précises <i></i>
	 * Test Prerequisites : Ce script utilise 4 paramètres en entrée <i></i>
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
                     String filePath = getParameter(0).toString();
                     File file = new File(filePath);
                     String tmpVal = getParameter(1).toString();//param1 correspond à la valeur à un insérer 
                     tmpVal = tmpVal.replaceAll("(\t|\r?\n)+", " ");
                     int coordX = getParameter(2).toInt();//param2 correspond au numéro de la colonne à laquelle on veut insérer tmpVal (écrasera toute donnée se trouvant sur cette ligne si déjà existante)
                     int coordY = getParameter(3).toInt();//param3 correspond au numéro de la ligne à laquelle on veut insérer tmpVal (écrasera toute donnée se trouvant sur cette ligne si déjà existante)
  
                     csv.open(file); // doit précéder un get 
            
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