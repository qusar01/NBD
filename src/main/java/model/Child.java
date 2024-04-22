package model;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.NonNull;

import java.util.UUID;

@Entity
@CqlName("children")
public class Child {

    @CqlName("child_id")
    @PartitionKey
    private UUID child_id;
    @NonNull
    @CqlName("name")
    private String name;
    @NonNull
    @CqlName("age")
    private int age;

    @NonNull
    @CqlName("gender")
    private String gender;

    @NonNull
    @CqlName("parent")
    private UUID parent;

    public Child() {

    }
    public Child(UUID id, String name, int age, String gender) {
        this.child_id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public UUID getChild_id() {
        return child_id;
    }

    public void setChild_id(UUID child_id) {
        this.child_id = child_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }

    public String getChildInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Child's name: ").append(name).append(" age: ").append(age).append(" gender: ").append(gender).append("\n");
        return sb.toString();
    }


}
