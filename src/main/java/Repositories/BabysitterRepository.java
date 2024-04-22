package Repositories;

import Mapping.BabysitterMgd;
import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

public class BabysitterRepository extends Repository<BabysitterMgd> {
    public BabysitterRepository() {
        super("babysitters", BabysitterMgd.class);
    }

    public BabysitterMgd findByName(String name) {
        MongoCollection<BabysitterMgd> collection = mongoBabysitterDB.getCollection(collectionName, BabysitterMgd.class);
        Bson filter = Filters.eq("name", name);
        return collection.find().filter(filter).first();
    }

    public void update(BabysitterMgd babysitter) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<BabysitterMgd> babysittersCollection = mongoBabysitterDB.getCollection(collectionName, BabysitterMgd.class);
            Bson filter = Filters.eq("_id", babysitter.getEntityId());

            Bson setUpdate = Updates.combine(
                    Updates.set("name", babysitter.getName()),
                    Updates.set("basePrice", babysitter.getBasePrice()),
                    Updates.set("isHired", babysitter.getIsHired()),
                    Updates.set("type", babysitter.getType())
            );

            babysittersCollection.updateOne(clientSession, filter, setUpdate);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
    }

    public void clearDatabase() {
        MongoCollection<BabysitterMgd> collection = mongoBabysitterDB.getCollection(collectionName, BabysitterMgd.class);
        collection.drop();
    }
}
