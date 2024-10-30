public class DomesticWashingMachine extends WashingMachine {
    public DomesticWashingMachine(int id, String model, int power, int maxRPM, java.sql.Date releaseDate, double price) {
        super(id,"Domestic", model, power, maxRPM, releaseDate, price); 
    }
    public DomesticWashingMachine() {
        super(0, "Domestic", "model", 0, 0, new java.sql.Date(2000, 12, 12), 0.0);

    }
}