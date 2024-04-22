import Producers.KafkaChildProducer;
import Mapping.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class KafkaChildProducerTest {

    KafkaChildProducer kafkaChildProducer = new KafkaChildProducer();

    @Test
    void createTopic() throws InterruptedException {
        KafkaChildProducer.createTopic("children", 3, (short)3);
    }

    @Test
    void isTopicExisting() {
        assertTrue(KafkaChildProducer.isTopicExisting("children"));
    }

    @Test
    void isTopicExistingFalse() {
        assertFalse(KafkaChildProducer.isTopicExisting("asdasd"));
    }

    @Test
    void produceTicket() throws ExecutionException, InterruptedException {
        Date end = new Date();
        long currentTimeMillis = end.getTime();
        long newTimeMillis = currentTimeMillis + (60 * 60 * 1000);
        end.setTime(newTimeMillis);
        BabysitterMgd babysitter = new InfantBabysitterMgd("Ania But", 30, 0, "infant");
        ParentMgd parent = new ParentMgd("Karolina", "Kowalska", null);
        ChildMgd child = new ChildMgd("Brajanek Kowalski", 6, "Chłop", parent);
        RentMgd rent = new RentMgd(end, babysitter, child, parent);

        kafkaChildProducer.produceChild(child);
    }
}