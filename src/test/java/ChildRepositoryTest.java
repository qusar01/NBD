import com.datastax.oss.driver.api.core.CqlSession;
import model.BabysitterDDL;
import model.Child;
import repository.ChildRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChildRepositoryTest {

    CqlSession session = BabysitterDDL.initSession();
    ChildRepository childRepository = new ChildRepository(session);

    void flush() {
        session.execute("TRUNCATE table rent_a_babysitter.children;");
    }
    @Test
    void get() {
        UUID id = new UUID(2,4);
        Child c = new Child(id,"Maciek", 4, "Chłop");
        Child c1 = childRepository.get(id);

        assertEquals(c.getChild_id(), c1.getChild_id());
    }

    @Test
    void get_not_exist() {
        Child c2 = childRepository.get(UUID.randomUUID());

        assertNull(c2);
    }

    @Test
    void add() {
        Child c = new Child(new UUID(1,4),"Maciek", 4, "Chłop");
        childRepository.add(c);

        assertNotNull(childRepository.get(new UUID(1,4)));
    }

    @Test
    void remove() {
        Child c = new Child(new UUID(1,4),"Maciek", 4, "Chłop");
        childRepository.remove(c);

        assertNull(childRepository.get(new UUID(1,4)));
    }

    @Test
    void update() {
        UUID id = new UUID(4,4);
        Child c = new Child(id,"Maciek", 4, "Chłop");
        childRepository.add(c);

        Child c1 = new Child(id,"Aneta", 4, "Chłop");
        childRepository.update(c1);

        assertEquals(c1.getName(), childRepository.get(id).getName());
    }
}