package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.NonNull;

import java.util.UUID;

@Entity
@CqlName("babysitters")
public class Babysitter {
    @CqlName("babysitter_id")
    @PartitionKey
    private UUID babysitter_id;
    @NonNull
    @CqlName("name")
    private String name;
    @NonNull
    @CqlName("basePrice")
    private float basePrice;
    @NonNull
    @CqlName("isHired")
    private boolean isHired;
    @NonNull
    @CqlName("type")
    private String type;

    public Babysitter() {

    }

    public Babysitter(UUID id, String name, float basePrice, boolean isHired, String type) {
        this.babysitter_id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.isHired = isHired;
        this.type = type;
    }

    public UUID getBabysitter_id() {
        return babysitter_id;
    }

    public void setBabysitter_id(UUID babysitter_id) {
        this.babysitter_id = babysitter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isHired() {
        return isHired;
    }

    public void setHired(boolean is_hired) {
        isHired = is_hired;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBabysitterInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", Base Price: ").append(basePrice).append(" PLN, Type: ").append(this.getType());
        return sb.toString();
    }

}


