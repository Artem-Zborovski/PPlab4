import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DomesticWashingMachine.class, name = "Domestic"),
    @JsonSubTypes.Type(value = IndustrialWashingMachine.class, name = "Industrial")
})
public abstract class WashingMachine {
    protected int id;
    protected String type;
    protected String model;
    protected int power;
    protected int maxSpeed;
    protected Date releaseDate;
    protected double price;

    public WashingMachine() {}
    public WashingMachine(int id, String type, String model, int power, int maxSpeed, Date releaseDate, double price) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }
    public int getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "WashingMachine{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", power=" + power +
                ", maxSpeed=" + maxSpeed +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
