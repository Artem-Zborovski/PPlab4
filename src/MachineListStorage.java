import java.util.ArrayList;
import java.util.List;

public class MachineListStorage extends MachineStorage {
    private List<WashingMachine> machines = new ArrayList<>();

    @Override
    public void addMachine(WashingMachine machine) {
        machines.add(machine);
    }
    public MachineListStorage() {
        machines = new ArrayList<>();  
    }

    
    @Override
    public WashingMachine getMachine(int id) {
        return machines.stream().filter(machine -> machine.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void removeMachine(int id) {
        machines.removeIf(machine -> machine.getId() == id);
    }

    @Override
    public void updateMachine(WashingMachine machine) {
        int index = machines.indexOf(getMachine(machine.getId()));
        if (index >= 0) {
            machines.set(index, machine);
        }
    }

    public List<WashingMachine> getMachines() {
        return machines;
    }
}
