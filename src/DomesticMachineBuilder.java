import java.sql.Date;

public class DomesticMachineBuilder extends MachineBuilder {

    @Override
    protected WashingMachine createMachineInstance() {
        return new DomesticWashingMachine();
    }

    @Override
    public void buildId(int id) {
        machine.id = id;
    }

    @Override
    public void buildModel(String model) {
        machine.model = model;
    }

    @Override
    public void buildPower(int power) {
        machine.power = power;
    }

    @Override
    public void buildMaxSpeed(int maxSpeed) {
        machine.maxSpeed = maxSpeed;
    }

    @Override
    public void buildPrice(double price) {
        machine.price = price;
    }

    @Override
    public void buildReleaseDate(java.sql.Date date) {
        machine.releaseDate = date;
        throw new UnsupportedOperationException("Unimplemented method 'buildReleaseDate'");
    }


}
