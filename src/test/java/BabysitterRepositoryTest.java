import com.datastax.oss.driver.api.core.CqlSession;
import model.BabysitterDDL;
import model.Babysitter;
import repository.BabysitterRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BabysitterRepositoryTest {

    CqlSession session = BabysitterDDL.initSession();
    BabysitterRepository babysitterRepository = new BabysitterRepository(session);

    void flush() {
        session.execute("TRUNCATE table rent_a_babysitter.babysitters;");
    }
    @Test
    void get() {
        UUID id = new UUID(2,4);
        Babysitter b = new Babysitter(id,"asd", 33, true, "ChildBabysitter");
        Babysitter b1 = babysitterRepository.get(id);

        assertEquals(b.getBabysitter_id(), b1.getBabysitter_id());
    }

    @Test
    void get_not_exist() {
        Babysitter b2 = babysitterRepository.get(UUID.randomUUID());

        assertNull(b2);
    }

    @Test
    void add() {
        Babysitter b3 = new Babysitter(new UUID(1,4),"asd", 33, true, "ChildBabysitter");
        babysitterRepository.add(b3);

        assertNotNull(babysitterRepository.get(new UUID(1,4)));
    }

    @Test
    void remove() {
        Babysitter b3 = new Babysitter(new UUID(1,4),"asd", 33, true, "ChildBabysitter");
        babysitterRepository.remove(b3);

        assertNull(babysitterRepository.get(new UUID(1,4)));
    }

    @Test
    void update() {
        UUID id = new UUID(4,4);
        Babysitter b = new Babysitter(id,"asd", 33, true, "ChildBabysitter");
        babysitterRepository.add(b);

        Babysitter b1 = new Babysitter(id,"asdefdefd", 33, true, "ChildBabysitter");
        babysitterRepository.update(b1);

        assertEquals(b1.getName(), babysitterRepository.get(id).getName());
    }
}