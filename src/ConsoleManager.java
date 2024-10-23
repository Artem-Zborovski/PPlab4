import java.util.Scanner;

public class ConsoleManager {
    public WashingMachine createMachine() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите id: ");
            int id = scanner.nextInt();
            System.out.print("Введите модель: ");
            String model = scanner.next();
            System.out.print("Введите мощность: ");
            int power = scanner.nextInt();
            System.out.print("Введите максимальные обороты: ");
            int maxRPM = scanner.nextInt();
            System.out.print("Введите дату выпуска (yyyy-mm-dd): ");
            String date = scanner.next();
            System.out.print("Введите цену: ");
            double price = scanner.nextDouble();

            return new DomesticWashingMachine(id, model, power, maxRPM, java.sql.Date.valueOf(date), price);
        }
    }
}
