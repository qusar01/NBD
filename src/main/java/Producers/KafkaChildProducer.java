package Producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.google.gson.Gson;
import Mapping.ChildMgd;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaFuture;

import java.net.InetAddress;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaChildProducer {

    private final String TOPIC = "children";
    private final KafkaProducer<String, String> producer;
    private final Gson mapper = new Gson();

    public KafkaChildProducer() {
        Properties producerConfig = new Properties();
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.CLIENT_ID_CONFIG, "local");
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
        producerConfig.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        this.producer = new KafkaProducer<String, String>(producerConfig);
    }

    public static void createTopic(String topicName, int partitions, short replicationFactory) throws InterruptedException {

        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");

        try (Admin admin = Admin.create(adminProperties)) {
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactory);
            CreateTopicsOptions options = new CreateTopicsOptions()
                    .timeoutMs(1000)
                    .validateOnly(false)
                    .retryOnQuotaViolation(true);

            CreateTopicsResult result = admin.createTopics(List.of(newTopic), options);
            KafkaFuture<Void> futureResult = result.values().get(topicName);
            futureResult.get();
        } catch (ExecutionException exception) {
            // jeżeli już isnieje ignoruj
            System.out.println(exception);
        }
    }

    public static boolean isTopicExisting(String topicName){

        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");
        Set<String> topics = null;
        try (Admin admin = Admin.create(adminProperties)) {
            topics = admin.listTopics().names().get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        assert topics != null;
        return topics.contains(topicName);
    }

    public void produceChild(ChildMgd child) throws ExecutionException, InterruptedException {
        String mappedChild = mapper.toJson(child, ChildMgd.class);
        Random random = new Random();
        int randomInt = random.nextInt(4) + 1;

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, "child" + randomInt, mappedChild);
        Future<RecordMetadata> sent = producer.send(record);
        sent.get();
    }
}