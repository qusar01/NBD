package Source;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "babysitter_type")
public abstract class BabysitterType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    protected double additionalCost;

    @Column
    protected String type;

    @Version
    private int version;

    public BabysitterType() {

    }

    public BabysitterType(String type, double additionalCost) {
        this.type = type;
        this.additionalCost = additionalCost;
    }

    public abstract double getAdditionalCost();

    public abstract String getType();

    public Long getId() {
        return id;
    }

    public void setAdditionalCost(double additionalCost) {
        this.additionalCost = additionalCost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

