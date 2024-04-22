import com.datastax.oss.driver.api.core.CqlSession;
import model.BabysitterDDL;
import model.Parent;
import repository.ParentRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ParentRepositoryTest {

    CqlSession session = BabysitterDDL.initSession();
    ParentRepository parentRepository = new ParentRepository(session);

    void flush() {
        session.execute("TRUNCATE table rent_a_babysitter.parents;");
    }
    @Test
    void get() {
        UUID id = new UUID(2,4);
        Parent p = new Parent(id,"Marcel", "Kowal");
        Parent p1 = parentRepository.get(id);

        assertEquals(p.getParent_id(), p1.getParent_id());
    }

    @Test
    void get_not_exist() {
        Parent p2 = parentRepository.get(UUID.randomUUID());

        assertNull(p2);
    }

    @Test
    void add() {
        Parent p = new Parent(new UUID(1,4),"Marcel", "Kowal");
        parentRepository.add(p);

        assertNotNull(parentRepository.get(new UUID(1,4)));
    }

    @Test
    void remove() {
        Parent p = new Parent(new UUID(1,4),"Marcel", "Kowal");
        parentRepository.remove(p);

        assertNull(parentRepository.get(new UUID(1,4)));
    }

    @Test
    void update() {
        UUID id = new UUID(4,4);
        Parent p = new Parent(id,"Marcel", "Kowal");
        parentRepository.add(p);

        Parent p1 = new Parent(id,"Antoni", "Kowal");
        parentRepository.update(p1);

        assertEquals(p1.getFirstName(), parentRepository.get(id).getFirstName());
    }
}