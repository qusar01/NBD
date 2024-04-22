package Mapping;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Getter
@Setter
public class AbstractEntityMgd implements Serializable {
    @BsonProperty("_id")
    @BsonId
    protected UniqueIdMgd entityId;

    public AbstractEntityMgd(UniqueIdMgd entityId) {
        this.entityId = entityId;
    }

}
