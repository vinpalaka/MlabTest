package json;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class TestMyJsonFlattener {
    @Test
    public void test() {
        InputStream fis = null;
        try {
            fis = new FileInputStream("input.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader jsonReader = Json.createReader(fis);
        JsonObject jsonObject = jsonReader.readObject();
        String expected = "{\n" +
                "\ta: 1, \n" +
                "\tb: 2, \n" +
                "\tc.d: 1, \n" +
                "\tc.e.f: true, \n" +
                "}";
        StringBuilder builder = new StringBuilder("{");
        builder.append(System.getProperty("line.separator"));
        MyJsonFlattener.outputJsonPath("", jsonObject, builder);
        builder.append("}");
        assertEquals(expected, builder.toString());
    }
}
