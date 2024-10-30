import java.util.Comparator;
import java.util.List;

public class MachineSorter {

    public static void sortByPrice(List<WashingMachine> machines) {
        machines.sort(Comparator.comparingDouble(WashingMachine::getPrice));
    }

    public static void sortByReleaseDate(List<WashingMachine> machin) {
        machin.sort(Comparator.comparing(WashingMachine::getReleaseDate));
    }
}
