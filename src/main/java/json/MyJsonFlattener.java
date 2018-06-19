package json;

import javax.json.*;
import java.io.*;
import java.util.Scanner;

public class MyJsonFlattener {
    public static void main(String[] args) {
        JsonReader jsonReader= getReaderFromInput();
        // Get the JsonObject structure from JsonReader.
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        StringBuilder builder = new StringBuilder("{");
        builder.append(System.getProperty("line.separator"));
        outputJsonPath("", jsonObject, builder);
        builder.append("}");
        System.out.println(builder.toString());
    }

    public static JsonReader getReaderFromInput() {
        JsonReader jsonReader= null;
        while(true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String file;
                while (true) {
                    System.out.println("Enter file Path to input json: ");
                    file = scanner.next();
                    if ((file.endsWith(".json"))) {
                        break;
                    }
                }
                InputStream fis = new FileInputStream(file);
                jsonReader = Json.createReader(fis);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Did not find file, please enter valid path");
            }
        }
        return jsonReader;
    }
    /**
     * Outputs path of each Json
     * @param s
     * @param jsonObject
     * @param builder
     */
    protected static void outputJsonPath(String s, JsonObject jsonObject, StringBuilder builder) {
        if(s.length() > 0) {
            s = s + ".";
        }
        for( String each : jsonObject.keySet()) {
            JsonValue value = jsonObject.get(each);
            JsonValue.ValueType valueType = value.getValueType();

            if(JsonValue.ValueType.OBJECT.equals(valueType)) {
                outputJsonPath(s + each, (JsonObject) value, builder);
            } else {
                builder.append("\t" + s + each + ": " + value + ", ");
                builder.append(System.getProperty("line.separator"));
            }
        }
    }
}
