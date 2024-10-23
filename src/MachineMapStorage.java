import java.util.HashMap;
import java.util.Map;

public class MachineMapStorage extends MachineStorage {
    private Map<Integer, WashingMachine> machines = new HashMap<>();

    @Override
    public void addMachine(WashingMachine machine) {
        machines.put(machine.getId(), machine);
    }

    @Override
    public WashingMachine getMachine(int id) {
        return machines.get(id);
    }

    @Override
    public void removeMachine(int id) {
        machines.remove(id);
    }

    @Override
    public void updateMachine(WashingMachine machine) {
        machines.put(machine.getId(), machine);
    }
}
