package Repositories;

import Mapping.ParentMgd;
import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

public class ParentRepository extends Repository<ParentMgd> {
    public ParentRepository() {
        super("parents", ParentMgd.class);
    }

    public ParentMgd findByName(String name) {
        MongoCollection<ParentMgd> collection = mongoBabysitterDB.getCollection(collectionName, ParentMgd.class);
        Bson filter = Filters.eq("lastName", name);
        return collection.find().filter(filter).first();
    }

    public void update(ParentMgd parent) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<ParentMgd> parentsCollection = mongoBabysitterDB.getCollection(collectionName, ParentMgd.class);
            Bson filter = Filters.eq("_id", parent.getEntityId());

            Bson setUpdate = Updates.combine(
                    Updates.set("firstName", parent.getFirstName()),
                    Updates.set("lastName", parent.getLastName()),
                    Updates.set("children", parent.getChildren())
            );

            parentsCollection.updateOne(clientSession, filter, setUpdate);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
    }

    public void clearDatabase() {
        MongoCollection<ParentMgd> collection = mongoBabysitterDB.getCollection(collectionName, ParentMgd.class);
        collection.drop();
    }
}
