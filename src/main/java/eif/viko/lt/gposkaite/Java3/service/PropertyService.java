package eif.viko.lt.gposkaite.Java3.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import eif.viko.lt.gposkaite.Java3.model.Property;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class PropertyService {
    private static final String JSON_FILE_PATH = "C:\\Users\\gabij\\Desktop\\Java3\\src\\main\\java\\eif\\viko\\lt\\gposkaite\\Java3\\properties.json";
    public List<Property> loadProperties() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<Property>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}