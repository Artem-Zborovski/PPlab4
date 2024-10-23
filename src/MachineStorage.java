public abstract class MachineStorage {
    public abstract void addMachine(WashingMachine machine);
    public abstract WashingMachine getMachine(int id);
    public abstract void removeMachine(int id);
    public abstract void updateMachine(WashingMachine machine);
    
}
