public class IndustrialWashingMachine extends WashingMachine {
    public IndustrialWashingMachine(int id, String model, int power, int maxSpeed, java.sql.Date releaseDate, double price) {
        super(id, "Industrial", model, power, maxSpeed, releaseDate, price);
    }
    public IndustrialWashingMachine(){
        super(0, "Industrial", "fdhkf", 0, 0, new java.sql.Date(2000, 12, 12), 0.0);
    }
}
