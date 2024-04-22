package model;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@CqlName("rents")
public class Rent {

    @CqlName("rent_id")
    @PartitionKey
    private UUID rent_id;

    @NonNull
    @CqlName("start")
    private LocalDate start;

    @NonNull
    @CqlName("end")
    private LocalDate end;

    @NonNull
    @CqlName("babysitter")
    private UUID babysitter;

    @NonNull
    @CqlName("child")
    private UUID child;

    @NonNull
    @CqlName("parent")
    private UUID parent;

    public Rent() {

    }
    public Rent(UUID id, LocalDate start, LocalDate end, UUID babysitter, UUID child, UUID parent) {
            this.rent_id = id;
            this.start = start;
            this.end = end;
            this.babysitter = babysitter;
            this.child = child;
            this.parent = parent;
    }

    public UUID getRent_id() {
        return rent_id;
    }

    public void setRent_id(UUID rent_id) {
        this.rent_id = rent_id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public UUID getBabysitter() {
        return babysitter;
    }

    public void setBabysitter(UUID babysitter) {
        this.babysitter = babysitter;
    }

    public UUID getChild() {
        return child;
    }

    public void setChild(UUID child) {
        this.child = child;
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }

    public String getRentInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Rent: ").append(rent_id).append(" start date: ").append(start).append(" end date: ").append(end)
                .append("\n");
        return info.toString();
    }

}




