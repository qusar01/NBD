package Mapping;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@BsonDiscriminator(key = "_clazz", value = "childbabysitter")
public class ChildBabysitterMgd extends BabysitterMgd {
    @BsonCreator
    public ChildBabysitterMgd(
            @BsonProperty("name") String name,
            @BsonProperty("basePrice")  double basePrice,
            @BsonProperty("isHired") int isHired,
            @BsonProperty("type") String type) {
        super(name, basePrice + 15.0, isHired, type);
    }

    @Override
    public String toString() {
        return "ChildBabysitter";
    }
}
