import java.io.File;

public class DataFileManager {
    private static DataFileManager instance;
    private File dataFile;

    private DataFileManager(String filePath) {
        this.dataFile = new File(filePath);
    }

    public static DataFileManager getInstance(String filePath) {
        if (instance == null) {
            instance = new DataFileManager(filePath);
        }
        return instance;
    }

    public File getDataFile() {
        return dataFile;
    }
}
