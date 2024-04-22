package Mapping;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@BsonDiscriminator(key = "_clazz", value = "toddlerbabysitter")
public class ToddlerBabysitterMgd extends BabysitterMgd {
    @BsonCreator
    public ToddlerBabysitterMgd(
            @BsonProperty("name") String name,
            @BsonProperty("basePrice")  double basePrice,
            @BsonProperty("isHired") int isHired,
            @BsonProperty("type") String type) {
        super(name, basePrice + 45.0, isHired, type);
    }

    @Override
    public String toString() {
        return "ToddlerBabysitter";
    }
}
