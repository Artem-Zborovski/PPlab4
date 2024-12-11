import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        MachineListStorage machineStorage = new MachineListStorage();
        machineStorage.addMachine(new DomesticWashingMachine(1, "AlexApanchen", 5000, 2000, java.sql.Date.valueOf("2006-06-01"), 500.0));
        machineStorage.addMachine(new IndustrialWashingMachine(2, "Lg", 3000, 1400, java.sql.Date.valueOf("2019-05-15"), 2000.0));

        // 1. Чтение данных с консоли для создания новой машины
        ConsoleManager consoleManager = new ConsoleManager();
        WashingMachine newMachine = consoleManager.createMachine();
        machineStorage.addMachine(newMachine);

        // 2. Чтение и запись машин в текстовый файл
        try {
            List<WashingMachine> machinesFromFile = FileManager.readMachinesFromFile("machin.txt");
            machinesFromFile.forEach(machineStorage::addMachine);

            System.out.println("Машины, прочитанные из файла:");
            machineStorage.getMachines().forEach(System.out::println);

            try {
                FileManager.writeToFile("machines.txt", machinesFromFile);
            } catch (Exception e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Ошибка чтения данных из файла: " + e.getMessage());
        }

        // 3. Сортировка и другие операции
        List<WashingMachine> machinesList = machineStorage.getMachines();
        MachineSorter.sortByPrice(machinesList);
        System.out.println("Сортировка по цене:");
        machinesList.forEach(System.out::println);

        MachineSorter.sortByReleaseDate(machinesList);
        System.out.println("Сортировка по дате выпуска:");
        machinesList.forEach(System.out::println);

        // 4. Шифрование
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
            XMLManager.writeMachinesToXML(machinesList, "machines.xml");
            System.out.println("Машины записаны в XML файл.");

            List<WashingMachine> machinesFromXML = XMLReader.readMachinesFromXML("machines.xml");
            System.out.println("Машины, прочитанные из XML:");
            machinesFromXML.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Ошибка работы с XML: " + e.getMessage());
        }

        // 7. Чтение/запись данных в/из JSON
        try {
            JSONManager.writeMachinesToJSON(machinesList, "machines.json");
            System.out.println("Машины записаны в JSON файл.");

            List<WashingMachine> machinesFromJSON = JSONReader.readMachinesFromJSON("machines.json");
            System.out.println("Машины, прочитанные из JSON:");
            machinesFromJSON.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Ошибка работы с JSON: " + e.getMessage());
        }

        // Запуск графического интерфейса
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WashingMachineApp application = new WashingMachineApp();
                application.show();
            }
        });
    }
}
