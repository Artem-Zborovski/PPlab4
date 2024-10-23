import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MachineListStorage machineStorage = new MachineListStorage();
        machineStorage.addMachine(new DomesticWashingMachine(1, "Model1", 1500, 1200, java.sql.Date.valueOf("2020-01-01"), 500.0));
        machineStorage.addMachine(new IndustrialWashingMachine(2, "Model2", 3000, 1400, java.sql.Date.valueOf("2019-05-15"), 2000.0));

        // 1. Чтение данных с консоли для создания новой машины
        ConsoleManager consoleManager = new ConsoleManager();
        WashingMachine newMachine = consoleManager.createMachine();
        machineStorage.addMachine(newMachine);

        // 2. Запись машин в текстовый файл
        try {
            List<String> lines = new ArrayList<>();
            for (WashingMachine machine : machineStorage.getMachines()) {
                lines.add(machine.toString());
            }
            FileManager.writeToFile("machines.txt", lines);
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

        // 3. Сортировка по цене
        List<WashingMachine> machinesList = machineStorage.getMachines();
        MachineSorter.sortByPrice(machinesList);  
        System.out.println("Сортировка по цене:");
        machinesList.forEach(System.out::println);  

        // 4. Шифрование данных
        try {
            String secretKey = "1234567812345678"; 
            String encryptedData = EncryptionManager.encrypt(newMachine.toString(), secretKey);
            String decryptedData = EncryptionManager.decrypt(encryptedData, secretKey);
            System.out.println("Зашифрованные данные: " + encryptedData);
            System.out.println("Расшифрованные данные: " + decryptedData);
        } catch (Exception e) {
            System.out.println("Ошибка шифрования: " + e.getMessage());
        }

        // 5. Архивация файла
        try {
            ZipManager.createZip("machines.txt", "machines.zip");
            System.out.println("Файл machines.txt был заархивирован в machines.zip");
        } catch (Exception e) {
            System.out.println("Ошибка архивации: " + e.getMessage());
        }

        // 6. Чтение/запись данных в/из XML
        try {
            XMLManager.writeMachinesToXML(machinesList, "machines.xml");  // Передаем список, а не массив
            System.out.println("Машины записаны в XML файл.");

            List<WashingMachine> machinesFromXML = XMLReader.readMachinesFromXML("machines.xml");
            System.out.println("Машины, прочитанные из XML:");
            machinesFromXML.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Ошибка работы с XML: " + e.getMessage());
        }

        // 7. Чтение/запись данных в/из JSON
        try {
            JSONManager.writeMachinesToJSON(machinesList, "machines.json");  // Передаем список, а не массив
            System.out.println("Машины записаны в JSON файл.");

            List<WashingMachine> machinesFromJSON = JSONReader.readMachinesFromJSON("machines.json");
            System.out.println("Машины, прочитанные из JSON:");
            machinesFromJSON.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Ошибка работы с JSON: " + e.getMessage());
        }
    }
}