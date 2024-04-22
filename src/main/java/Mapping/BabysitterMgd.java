package Mapping;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
public abstract class BabysitterMgd extends AbstractEntityMgd {
    @BsonProperty("name")
    private String name;
    @BsonProperty("basePrice")
    private double basePrice;
    @BsonProperty("isHired")
    private int isHired;
    @BsonProperty("type")
    private String type;
    @BsonCreator
    public BabysitterMgd(
            @BsonProperty("_id") UniqueIdMgd entityId,
            @BsonProperty("name") String name,
            @BsonProperty("basePrice")  double basePrice,
            @BsonProperty("isHired") int isHired,
            @BsonProperty("type") String type) {
        super(entityId);
        this.name = name;
        this.basePrice = basePrice;
        this.isHired = isHired;
        this.type = type;
    }

    public BabysitterMgd(String name, double basePrice, int isHired, String type) {
        super(new UniqueIdMgd());
        this.name = name;
        this.basePrice = basePrice;
        this.isHired = isHired;
        this.type = type;
    }

    public BabysitterMgd(){
        super(new UniqueIdMgd());
    }

}
