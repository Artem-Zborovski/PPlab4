import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JSONManager {

    public static void writeMachinesToJSON(List<WashingMachine> machines, String outputFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(outputFile), machines);
    }
    public static List<WashingMachine> readMachinesFromJSON(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        List<WashingMachine> machines = objectMapper.readValue(new File(filePath), new TypeReference<List<WashingMachine>>() {});
        
        return machines;
    }
}
