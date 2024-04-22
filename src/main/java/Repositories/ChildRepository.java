package Repositories;

import Mapping.ChildMgd;
import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class ChildRepository extends Repository<ChildMgd> {
    public ChildRepository() {
        super("children", ChildMgd.class);
    }

    public ChildMgd findByName(String name) {
        MongoCollection<ChildMgd> collection = mongoBabysitterDB.getCollection(collectionName, ChildMgd.class);
        Bson filter = Filters.eq("name", name);
        return collection.find().filter(filter).first();
    }

    public void update(ChildMgd child) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<ChildMgd> childrenCollection = mongoBabysitterDB.getCollection(collectionName, ChildMgd.class);
            Bson filter = Filters.eq("_id", child.getEntityId());

            Bson setUpdate = Updates.combine(
                    Updates.set("name", child.getName()),
                    Updates.set("age", child.getAge()),
                    Updates.set("gender", child.getGender()),
                    Updates.set("parent", child.getParent())
            );

            childrenCollection.updateOne(clientSession, filter, setUpdate);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
    }

    public ChildMgd delete(UUID id) {
        ClientSession clientSession = mongoClient.startSession();
        ChildMgd child;
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<ChildMgd> collection = mongoBabysitterDB.getCollection(collectionName, ChildMgd.class);
            Bson filter = eq("_id", id);
            child = collection.findOneAndDelete(filter);
            clientSession.commitTransaction();
            return child;
        } catch (Exception e) {
            clientSession.abortTransaction();
            return null;
        } finally {
            clientSession.close();
        }
    }

    public void clearDatabase() {
        MongoCollection<ChildMgd> collection = mongoBabysitterDB.getCollection(collectionName, ChildMgd.class);
        collection.drop();
    }
}
