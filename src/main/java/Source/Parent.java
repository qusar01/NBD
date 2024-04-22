package Source;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "parent")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(min = 1, max = 50)
    private String firstName;

    @Column(nullable = false)
    @Length(min = 1, max = 50)
    private String lastName;

    @OneToMany(mappedBy = "parent")
    private List<Child> children = new ArrayList<>();

    @Version
    private int version;

    public Parent() {

    }

    public Parent(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = new ArrayList<>();
    }

    public List<Child> getChildren() {
        return children;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOfChildren() {
        return children.size();
    }

    public void addChild(Child child) {
        if (child == null) {
            System.out.println("Child was not added correctly");
        } else {
            children.add(child);
        }
    }

    public Child getChild(String name) {
        for (Child child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    public String getParentInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parent: ").append(firstName).append(" ").append(lastName).append(", ")
                .append(", registered children:\n");

        for (Child child : children) {
            sb.append(child.getChildInfo()).append("\n");
        }
        return sb.toString();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
