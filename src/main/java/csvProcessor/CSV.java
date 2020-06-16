package csvProcessor;

import java.io.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;
 
public class CSV {
 
    private HashMap<Point, String> parameters = new HashMap<Point, String>();        //HashMap de String avec pour clé une variable Point définie par des coordonnées x, y 
    private int _cols;     //nombre de colonnes
    private int _rows;     //nombre de lignes
 
    public void open(File file) throws FileNotFoundException, IOException {
        open(file, ',');              //séparateur fixé ici car un seul type de fichier traité : CSV
    }
 
    public void open(File file, char delimiter) throws FileNotFoundException, IOException { //ouvre un CSV, lit, et stock les données dans la HashMap parameters
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(Character.toString(delimiter));
 
        clear();
 
        while(scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(Character.toString(delimiter));
 
            int col = 0;
            for ( String value: values ) {
                parameters.put(new Point(col, _rows), value);
                _cols = Math.max(_cols, ++col);
            }
            _rows++;
        }
        scanner.close();
    }

    public void addLine(String string, String delimiter, int row) {
    	String[] values = string.split(delimiter);
    	int col=0;
    	Point key;
    	while (_cols>=0) {    //(parameters.containsKey(new Point(col, row))==true) {
    		parameters.remove(new Point(_cols,row));
    		_cols--;
    	}
    	col = 0;
    	for ( String value: values ) {
    		System.out.println(value);
            parameters.put(new Point(col, row), value);
            _cols = Math.max(_cols, ++col);
        }
    	_rows = Math.max(_rows, row+1);
    }



    /*public void addLine(String string, String delimiter, int row) {   OLD VERSION
    	String[] values = string.split(delimiter);
    	int col = 0;
    	for ( String value: values ) {
    		System.out.println(value);
            parameters.put(new Point(col, row), value);
            _cols = Math.max(_cols, ++col);
        }
    	_rows = Math.max(_rows, row+1);
    }*/


    public void split(String string, String delimiter, int col, int row) { // La colonne placée en entrée ici est la colonne à partir de laquelle commencera l'opération : 2
    	String[] values = string.split(delimiter);
    	for (String value : values) {
    		parameters.put(new Point(col, row), value);
    		_cols = Math.max(_cols,  ++col);
    	}
        _rows = Math.max(_rows, ++row);
    }
 
    public void save(File file) throws IOException {
        save(file, ',');         //séparateur fixé ici car un seul type de fichier traité : CSV
    }
 
    public void save(File file, char delimiter) throws IOException { //écrase les données de la HashMap sur les anciennes données du fichier CSV
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
 
        for (int row = 0; row < _rows; row++) {
            for (int col = 0; col < _cols; col++) {
                Point key = new Point(col, row);
                if (parameters.containsKey(key)) {
                    bw.write(parameters.get(key));
                }
 
                if ((col + 1) < _cols) {
                    bw.write(delimiter);
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
 
    public String get(int col, int row) {  //retourne une cellule précise 
        String val = "";
        Point key = new Point(col, row);
        if (parameters.containsKey(key)) {
            val = parameters.get(key);
        }
        else{
            val = "non trouvé";
        }
        return val;
    }
 
    public void put(int col, int row, String value) {  //écrase une cellule précise ou agrandit les dimensions _cols, _rows de la HashMap
        parameters.put(new Point(col, row), value);
        _cols = Math.max(_cols, col+1);
        _rows = Math.max(_rows, row+1);
    }
 
    public void clear() { //nettoie la HashMap
        parameters.clear();
        _cols = 0;
        _rows = 0;
    }
 
    public int rows() { //retourne le nombre de lignes
        return _rows;
    }
 
    public int cols() { //retourne le nombre de colonnes
        return _cols;
    }
    
}