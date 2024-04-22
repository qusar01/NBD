package Repositories;

import Mapping.ChildMgd;
import Mapping.UniqueIdCodecProviderMgd;
import Mapping.UniqueIdMgd;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public abstract class Repository<T> implements AutoCloseable, RepositoryInterface<T>{
    protected ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single");
    protected MongoCredential credential = MongoCredential.createCredential("admin", "admin", "adminpassword".toCharArray());

    protected CodecRegistry pojoCodecRegistry =
            CodecRegistries.fromProviders(PojoCodecProvider.builder()
                    .automatic(true)
                    .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
                    .build());
    protected MongoClient mongoClient;
    protected MongoDatabase mongoBabysitterDB;
    protected final String collectionName;
    private final Class<T> entityClass;


    public Repository(String collectionName, Class<T> entityClass) {
        this.collectionName = collectionName;
        this.entityClass = entityClass;
        initDbConnection();
    }
    protected void initDbConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(CodecRegistries.fromRegistries(
                        CodecRegistries.fromProviders(new UniqueIdCodecProviderMgd()),
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry
                ))
                .build();

        mongoClient = MongoClients.create(settings);
        mongoBabysitterDB = mongoClient.getDatabase("babysitterdb");
    }
    @Override
    public T get(UUID id) {
        ArrayList<T> list = find(id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    @Override
    public boolean add(T object) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<T> collection = mongoBabysitterDB.getCollection(collectionName, entityClass);
            collection.insertOne(object);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
        return true;
    }

    public ArrayList<T> find(UUID id) {
        Bson filter = eq("_id", id);

        ArrayList<T> ls = mongoBabysitterDB.getCollection(collectionName, entityClass)
                .find(filter)
                .into(new ArrayList<>());
        return ls;
    }

    public List<T> findAll() {
        MongoCollection<T> collection = mongoBabysitterDB.getCollection(collectionName, entityClass);
        return collection.find().into(new ArrayList<>());
    }
    @Override
    public void update(T object) {
    }

    public T delete(UUID id) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction(TransactionOptions.builder()
                    .readConcern(ReadConcern.SNAPSHOT)
                    .writeConcern(WriteConcern.MAJORITY)
                    .build());
            MongoCollection<T> collection = mongoBabysitterDB.getCollection(collectionName, entityClass);
            Bson filter = eq("_id", id);
            collection.deleteOne(filter);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
        return null;
    }

    @Override
    public void close() {
        mongoClient.close();
    }

}