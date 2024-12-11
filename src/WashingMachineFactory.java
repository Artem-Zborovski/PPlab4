import java.sql.Date;
public class WashingMachineFactory {
    private MachineBuilder builder;

    public void setBuilder(MachineBuilder builder) {
        this.builder = builder;
    }

    public WashingMachine assembleMachine(int id, String model, int power, int maxSpeed, Date releaseDate, double price) {
        builder.createNewMachine();
        builder.buildId(id);
        builder.buildModel(model);
        builder.buildPower(power);
        builder.buildMaxSpeed(maxSpeed);
        builder.buildReleaseDate(releaseDate);
        builder.buildPrice(price);
        return builder.getMachine();
    }
}
