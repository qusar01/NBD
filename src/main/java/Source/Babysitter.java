package Source;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "babysitter")
public class Babysitter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(min = 1, max = 50)
    private String name;

    @Column
    @Min(0)
    @Max(1000)
    private static double basePrice;

    @Column
    private boolean isHired;

    @ManyToOne
    private BabysitterType type;

    @Version
    private int version;

    public Babysitter() {

    }

    public Babysitter(String name, double basePrice, boolean isHired) {
        this.name = name;
        this.basePrice = basePrice;
        this.isHired = isHired;
    }

    public Long getId() {
        return id;
    }

    public void setHired(boolean hired) {
        isHired = hired;
    }

    public boolean isHired() {
        return isHired;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static double getBasePrice() {
        return basePrice;
    }


    public BabysitterType getType() {
        return type;
    }

    public void setType(BabysitterType type) {
        this.type = type;
    }

    public String getBabysitterInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", Base Price: ").append(basePrice).append(" PLN, Type: ").append(getType().getType());
        return sb.toString();
    }

    public double getTotalPrice() {
        return basePrice + type.getAdditionalCost();
    }
}


