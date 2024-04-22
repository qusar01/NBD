import com.datastax.oss.driver.api.core.CqlSession;
import model.BabysitterDDL;
import model.Rent;
import repository.RentRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RentRepositoryTest {

    CqlSession session = BabysitterDDL.initSession();
    RentRepository rentRepository = new RentRepository(session);

    void flush() {
        session.execute("TRUNCATE table rent_a_babysitter.rents;");
    }
    @Test
    void get() {
        UUID id = new UUID(2,4);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Rent r = new Rent(id,today,tomorrow,id,id,id);
        Rent r1 = rentRepository.get(id);

        assertEquals(r.getRent_id(), r1.getRent_id());
    }

    @Test
    void get_not_exist() {
        Rent r2 = rentRepository.get(UUID.randomUUID());

        assertNull(r2);
    }

    @Test
    void add() {
        UUID id = new UUID(1,4);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Rent r = new Rent(id,today,tomorrow,id,id,id);
        rentRepository.add(r);

        assertNotNull(rentRepository.get(new UUID(1,4)));
    }

    @Test
    void remove() {
        UUID id = new UUID(1,4);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Rent r = new Rent(id,today,tomorrow,id,id,id);
        rentRepository.remove(r);

        assertNull(rentRepository.get(new UUID(1,4)));
    }

    @Test
    void update() {
        UUID id = new UUID(4,4);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Rent r = new Rent(id,today,tomorrow,id,id,id);
        rentRepository.add(r);

        Rent r1 = new Rent(id,today,tomorrow.plusDays(1),id,id,id);
        rentRepository.update(r1);

        assertEquals(r1.getEnd(), rentRepository.get(id).getEnd());
    }
}