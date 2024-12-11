import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.FilterFactory.FilterNotCreatedException;

public class FileManager {

    public static List<WashingMachine> readMachinesFromFile(String fileName) throws IOException {
        List<WashingMachine> machines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String model = parts[1];
                    int power = Integer.parseInt(parts[2]);
                    int maxSpeed = Integer.parseInt(parts[3]);
                    Date releaseDate = Date.valueOf(parts[4]);
                    double price = Double.parseDouble(parts[5]);
    
                    WashingMachine machine = new DomesticWashingMachine(id, model, power, maxSpeed, releaseDate, price);
                    machines.add(machine);
                } catch (Exception e) {
                    System.err.println("Ошибка формата данных в строке: " + line + ": " + e.getMessage());
                }
            }
        }
        return machines;
    }

    public static void writeToFile(String fileName, List<WashingMachine> machines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (WashingMachine machine : machines) {
                writer.write(String.format("%d,%s,%d,%d,%s,%.2f",
                        machine.getId(),
                        machine.getModel(),
                        machine.getPower(),
                        machine.getMaxSpeed(),
                        machine.getReleaseDate(),
                        machine.getPrice()));
                writer.newLine();
            }
        }
    }
     
}
