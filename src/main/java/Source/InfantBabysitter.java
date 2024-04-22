package Source;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("infant_babysitter")
public class InfantBabysitter extends BabysitterType {
    public InfantBabysitter() {
        super("Infant Babysitter", 75.0);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public double getAdditionalCost() {
        return additionalCost;
    }

    @Override
    public String getType() {
        return type;
    }
}
