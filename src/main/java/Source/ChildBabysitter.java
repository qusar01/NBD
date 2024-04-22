package Source;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("child_babysitter")
public class ChildBabysitter extends BabysitterType {
    public ChildBabysitter() {
        super("Child Babysitter (Ages 3-8)", 25.0);
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
