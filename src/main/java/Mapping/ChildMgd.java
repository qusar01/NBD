package Mapping;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Objects;

@Getter
@Setter
public class ChildMgd extends AbstractEntityMgd {
    @BsonProperty("name")
    private String name;
    @BsonProperty("age")
    private int age;
    @BsonProperty("gender")
    private String gender;
    @BsonProperty("parent")
    private ParentMgd parent;
    @BsonCreator
    public ChildMgd(
            @BsonProperty("_id") UniqueIdMgd entityId,
            @BsonProperty("age") int age,
            @BsonProperty("gender") String gender,
            @BsonProperty("parent") ParentMgd parent) {
        super(entityId);
        this.age = age;
        this.gender = gender;
        this.parent = parent;
    }

    public ChildMgd(String name, int age, String gender, ParentMgd parent) {
        super(new UniqueIdMgd());
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.parent = parent;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildMgd childMgd = (ChildMgd) o;
        return getAge() == childMgd.getAge() &&
                Objects.equals(getName(), childMgd.getName()) &&
                Objects.equals(getGender(), childMgd.getGender()) &&
                Objects.equals(getParent(), childMgd.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getGender(), getParent());
    }
}
