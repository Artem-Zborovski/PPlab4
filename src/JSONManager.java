import java.io.File;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JSONManager {

    public static void writeMachinesToJSON(List<WashingMachine> machines, String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), machines);
    }
}
