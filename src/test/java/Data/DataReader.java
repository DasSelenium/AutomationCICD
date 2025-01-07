package Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//Data//DataProvider.json"));

        //String to hashmap JacksonDataBind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        return data;

    }
}