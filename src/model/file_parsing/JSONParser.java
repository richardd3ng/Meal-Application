package model.file_parsing;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class JSONParser {

    public static JSONObject pathToJSON(String jsonFilePath) {
        return loadFile(new File(jsonFilePath));
    }

    /**
     * takes in a json file and then outputs a jsonobject object.
     *
     * @param file - file object of a json file
     * @return a jsonobject object
     */
    private static JSONObject loadFile(File file) {
        String currFileString = null;
        try {
            currFileString = readFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return new JSONObject(currFileString);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * reads each line of the file and adds it to a string. The string is then used to build the jsonobject
     */
    private static String readFile(File currFile) throws FileNotFoundException {
        Scanner scan = new Scanner(currFile);
        String currFileString = "";
        while (scan.hasNext()) {
            currFileString += (scan.nextLine());
        }
        scan.close();
        return currFileString;
    }
}
