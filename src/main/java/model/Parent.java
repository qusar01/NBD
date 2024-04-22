package model;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@CqlName("parents")
public class Parent {

    @CqlName("parent_id")
    @PartitionKey
    private UUID parent_id;

    @NonNull
    @CqlName("first_name")
    private String firstName;

    @NonNull
    @CqlName("last_name")
    private String lastName;

    @NonNull
    @CqlName("children")
    private List<UUID> children = new ArrayList<>();

    public Parent() {

    }

    public Parent(UUID id, String firstName, String lastName) {
        this.parent_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = new ArrayList<>();
    }

    public UUID getParent_id() {
        return parent_id;
    }

    public void setParent_id(UUID parent_id) {
        this.parent_id = parent_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UUID> getChildren() {
        return children;
    }

    public void setChildren(List<UUID> children) {
        this.children = children;
    }

}
