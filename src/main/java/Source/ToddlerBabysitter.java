package Source;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("toddler_babysitter")
public class ToddlerBabysitter extends BabysitterType {

    public ToddlerBabysitter() {
        super("Toddler Babysitter (Ages 1-3)", 50.0);
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
