package Source;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(min = 1, max = 50)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Length(min = 1, max = 50)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Version
    private int version;

    public Child() {

    }
    public Child(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getChildInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Child's name: ").append(name).append(" age: ").append(age).append(" gender: ").append(gender).append("\n");
        return sb.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void addParent(Parent parent) {
        this.parent = parent;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
