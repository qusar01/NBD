package Mapping;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@BsonDiscriminator(key = "_clazz", value = "infantbabysitter")
public class InfantBabysitterMgd extends BabysitterMgd {
    @BsonCreator
    public InfantBabysitterMgd(
            @BsonProperty("name") String name,
            @BsonProperty("basePrice")  double basePrice,
            @BsonProperty("isHired") int isHired,
            @BsonProperty("type") String type) {
        super(name, basePrice + 30.0, isHired, type);
    }

    @Override
    public String toString() {
        return "InfantBabysitter";
    }
}
