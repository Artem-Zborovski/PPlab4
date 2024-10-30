import java.util.Scanner;
import java.sql.Date;

public class ConsoleManager {

    private static final Scanner scanner = new Scanner(System.in);

    public WashingMachine createMachine() {
        int id = readInt("ID");
        String model = readString("модель");
        int power = readInt("мощность");
        int spinSpeed = readInt("максимальные обороты");
        Date releaseDate = readDate("дату выпуска (YYYY-MM-DD)");
        double price = readDouble("цену");

        // Читаем тип машины и создаем нужный объект
        String type = readString("тип (domestic или industrial)").toLowerCase();
        if (type.equals("domestic")) {
            return new DomesticWashingMachine(id, model, power, spinSpeed, releaseDate, price);
        } else if (type.equals("industrial")) {
            return new IndustrialWashingMachine(id, model, power, spinSpeed, releaseDate, price);
        } else {
            System.out.println("Некорректный тип машины. Используем тип по умолчанию (domestic).");
            return new DomesticWashingMachine(id, model, power, spinSpeed, releaseDate, price);
        }
    }

    // Чтение строки с консоли
    private String readString(String prompt) {
        System.out.print("Введите " + prompt + ": ");
        return scanner.nextLine().trim();
    }

    // Чтение целого числа с обработкой исключений
    private int readInt(String prompt) {
        while (true) {
            System.out.print("Введите " + prompt + ": ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное целое число.");
            }
        }
    }

    // Чтение вещественного числа с обработкой исключений
    private double readDouble(String prompt) {
        while (true) {
            System.out.print("Введите " + prompt + ": ");
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
            }
        }
    }

    // Чтение даты с обработкой исключений
    private Date readDate(String prompt) {
        while (true) {
            System.out.print("Введите " + prompt + ": ");
            try {
                return Date.valueOf(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Введите дату в формате YYYY-MM-DD.");
            }
        }
    }
}
