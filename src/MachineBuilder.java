import java.sql.Date;

public abstract class MachineBuilder {
    protected WashingMachine machine;

    public void createNewMachine() {
        machine = createMachineInstance();
    }

    protected abstract WashingMachine createMachineInstance();

    public abstract void buildId(int id);
    public abstract void buildModel(String model);
    public abstract void buildPower(int power);
    public abstract void buildMaxSpeed(int maxSpeed);
    public abstract void buildReleaseDate(Date date);
    public abstract void buildPrice(double price);

    public WashingMachine getMachine() {
        return machine;
    }
}
