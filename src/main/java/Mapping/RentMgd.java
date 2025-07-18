package Mapping;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

@Getter
@Setter
public class RentMgd extends AbstractEntityMgd {
    @BsonProperty("start")
    private Date start;
    @BsonProperty("end")
    private Date end;
    @BsonProperty(value = "babysitter", useDiscriminator = true)
    private BabysitterMgd babysitter;
    @BsonProperty("child")
    private ChildMgd child;
    @BsonProperty("parent")
    private ParentMgd parent;

    @BsonCreator
    public RentMgd(
            @BsonProperty("_id") UniqueIdMgd entityId,
            @BsonProperty("start") Date start,
            @BsonProperty("end") Date end,
            @BsonProperty("babysitter") BabysitterMgd babysitter,
            @BsonProperty("child") ChildMgd child,
            @BsonProperty("parent") ParentMgd parent) {
        super(entityId);
        this.start = new Date();
        this.end = end;
        this.babysitter = babysitter;
        this.child = child;
        this.parent = parent;
    }

    public RentMgd(Date end, BabysitterMgd babysitter, ChildMgd child, ParentMgd parent) {
        super(new UniqueIdMgd());
        if(babysitter.getIsHired() == 1) {
            throw new IllegalArgumentException("Opiekunka jest już wynajęta");
        } else {
            babysitter.setIsHired(1);
            this.start = new Date();
            this.end = end;
            this.babysitter = babysitter;
            this.child = child;
            this.parent = parent;
        }
    }
}
