package Repositories;

import Mapping.RentMgd;
import Mapping.BabysitterMgd;
import Mapping.ChildMgd;
import Mapping.ParentMgd;
import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;

public class RentRepository extends Repository {


    public RentRepository() {
        super("rents", RentMgd.class);
    }
    public List<RentMgd> findByBabysitter(BabysitterMgd babysitter) {
        MongoCollection<RentMgd> collection = mongoBabysitterDB.getCollection(collectionName, RentMgd.class);
        Bson filter = Filters.eq("babysitter._id", babysitter.getEntityId());
        return collection.find().filter(filter).into(new ArrayList<>());
    }
    public RentMgd findByChild(ChildMgd child) {
        MongoCollection<RentMgd> collection = mongoBabysitterDB.getCollection(collectionName, RentMgd.class);
        Bson filter = Filters.eq("child._id", child.getEntityId());
        return collection.find().filter(filter).first();
    }

    public RentMgd findByParent(ParentMgd parent) {
        MongoCollection<RentMgd> collection = mongoBabysitterDB.getCollection(collectionName, RentMgd.class);
        Bson filter = Filters.eq("parent._id", parent.getEntityId());
        return collection.find().filter(filter).first();
    }
    public void update(RentMgd rent) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<RentMgd> rentsCollection = mongoBabysitterDB.getCollection(collectionName, RentMgd.class);
            Bson filter = Filters.eq("_id", rent.getEntityId());
            Bson setUpdate = Updates.combine(
                    Updates.set("start", rent.getStart()),
                    Updates.set("end", rent.getEnd()),
                    Updates.set("babysitter", rent.getBabysitter()),
                    Updates.set("child", rent.getChild()),
                    Updates.set("parent", rent.getParent())
            );
            rentsCollection.updateOne(clientSession, filter, setUpdate);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }

    }
    public void clearDatabase() {
        MongoCollection<RentMgd> collection = mongoBabysitterDB.getCollection(collectionName, RentMgd.class);
        collection.drop();
    }



}
