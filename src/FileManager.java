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

    public static List<WashingMachine> readMachinesFromFile(String filePath) {
        List<WashingMachine> machines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0; 

            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    String[] parts = line.split(",");

                    // Проверка, что строка имеет правильное количество элементов
                    if (parts.length < 7) {
                        throw new IllegalArgumentException("Некорректный формат данных: недостаточно элементов");
                    }

                    // Чтение и преобразование полей
                    int id = Integer.parseInt(parts[0].trim());
                    String model = parts[1].trim();
                    int power = Integer.parseInt(parts[2].trim());
                    int maxSpeed = Integer.parseInt(parts[3].trim());
                    Date releaseDate = Date.valueOf(parts[4].trim());
                    double price = Double.parseDouble(parts[5].trim());
                    String type = parts[6].trim().toLowerCase();

                    // Создание объекта машины в зависимости от типа
                    WashingMachine machine;
                    if (type.equals("domestic")) {
                        machine = new DomesticWashingMachine(id, model, power, maxSpeed, releaseDate, price);
                    } else if (type.equals("industrial")) {
                        machine = new IndustrialWashingMachine(id, model, power, maxSpeed, releaseDate, price);
                    } else {
                        throw new IllegalArgumentException("Неизвестный тип машины: " + type);
                    }

                    machines.add(machine);

                } catch (NumberFormatException e) {
                    System.out.println("Ошибка формата числовых данных в строке " + lineNumber + ": " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка данных в строке " + lineNumber + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return machines;
    }
    public static void writeToFile(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
    // Преобразование объекта WashingMachine в строку
    private static String machineToString(WashingMachine machine) {
        return machine.getId() + "," +
               machine.getModel() + "," +
               machine.getPower() + "," +
               machine.getMaxSpeed() + "," +
               machine.getReleaseDate() + "," +
               machine.getPrice() + "," +
               (machine instanceof DomesticWashingMachine ? "domestic" : "industrial");
    }
}
