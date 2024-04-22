package model;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;

import java.net.InetSocketAddress;

import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createKeyspace;
import static util.CassandraConstants.*;

public class BabysitterDDL {

    private static CqlSession session;

    public static CqlSession initSession() {
        CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .addContactPoint(new InetSocketAddress("localhost", 9043))
                .withLocalDatacenter("dc1")
                .withAuthCredentials("cassandra", "cassandrapassword")
                .withKeyspace(CqlIdentifier.fromCql("rent_a_babysitter"))
                .build();

//        CreateKeyspace keyspace = createKeyspace(RENT_A_BABYSITTER)
//                .ifNotExists()
//                .withSimpleStrategy(2)
//                .withDurableWrites(true);
//        SimpleStatement se = keyspace.build();
//        session.execute(se);
//
//        SimpleStatement createBabysitters = SchemaBuilder.createTable(BABYSITTERS)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("babysitter_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("name"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("basePrice"), DataTypes.FLOAT)
//                .withColumn(CqlIdentifier.fromCql("hired"), DataTypes.BOOLEAN)
//                .withColumn(CqlIdentifier.fromCql("type"), DataTypes.TEXT)
//                .build();
//        session.execute(createBabysitters);
//
//        SimpleStatement createChildren = SchemaBuilder.createTable(CHILDREN)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("child_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("name"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("age"), DataTypes.INT)
//                .withColumn(CqlIdentifier.fromCql("gender"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("parent"), DataTypes.UUID)
//                .build();
//        session.execute(createChildren);
//
//        SimpleStatement createParents = SchemaBuilder.createTable(PARENTS)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("parent_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("first_name"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("last_name"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("children"), DataTypes.listOf(DataTypes.UUID))
//                .build();
//        session.execute(createParents);
//
//        SimpleStatement createRents = SchemaBuilder.createTable(RENTS)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("rent_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("start"), DataTypes.DATE)
//                .withColumn(CqlIdentifier.fromCql("end"), DataTypes.DATE)
//                .withColumn(CqlIdentifier.fromCql("babysitter"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("child"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("parent"), DataTypes.UUID)
//                .build();
//        session.execute(createRents);

        return session;
    }
}