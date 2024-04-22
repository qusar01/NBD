package Mapping;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParentMgd extends AbstractEntityMgd {
    @BsonProperty("firstName")
    private String firstName;
    @BsonProperty("lastName")
    private String lastName;
    @BsonProperty("children")
    @NonNull
    private List<ChildMgd> children = new ArrayList<>();

    @BsonCreator
    public ParentMgd(
            @BsonProperty("_id") UniqueIdMgd entityId,
            @BsonProperty("firstName") String firstName,
            @BsonProperty("lastName") String lastName,
            @BsonProperty("children") List<ChildMgd> children) {
        super(entityId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = children;
    }

    public ParentMgd(String firstName, String lastName, List<ChildMgd> children) {
        super(new UniqueIdMgd());
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = children;
    }
}
