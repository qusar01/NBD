import Source.*;
import Repositories.BabysitterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class BabysitterRepositoryUnitTest {

    private static EntityManager entityManager;
    private BabysitterRepository babysitterRepository;

    @BeforeAll
    public static void setUpEntityManager() {
        entityManager = Persistence.createEntityManagerFactory("babysitter_pu").createEntityManager();
    }

    @AfterAll
    public static void closeEntityManager() {
        entityManager.close();
    }

    @BeforeEach
    public void setUp() {
        babysitterRepository = new BabysitterRepository(entityManager);
    }

    @Test
    public void isHiredTest() {
        Babysitter babysitter = new Babysitter("Anna", 30.0, false);
        babysitter = entityManager.merge(babysitter);

        Babysitter hiredBabysitter = babysitterRepository.hire(babysitter.getId());

        assertTrue(hiredBabysitter.isHired());
    }

    @Test
    public void hireNonExistentBabysitterTest() {
        Babysitter hiredBabysitter = babysitterRepository.hire(999L);

        assertNull(hiredBabysitter);
    }

    @Test
    public void changeTypeTest() {
        Babysitter babysitter = new Babysitter("Anna", 30.0, false);
        BabysitterType oldType = new ChildBabysitter();
        BabysitterType newType = new InfantBabysitter();
        babysitter.setType(oldType);
        babysitter = entityManager.merge(babysitter);

        Babysitter babysitterWithNewType = babysitterRepository.changeType(babysitter.getId(), newType);

        assertEquals(newType, babysitterWithNewType.getType());
    }

    @Test
    public void changeTypeOfNonExistentBabysitterTest() {
        BabysitterType newType = new ChildBabysitter();

        Babysitter babysitterWithNewType = babysitterRepository.changeType(999L, newType);

        assertNull(babysitterWithNewType);
    }

    @Test
    public void findByIdTest() {
        Babysitter babysitter = new Babysitter("John", 40.0, false);
        babysitter = entityManager.merge(babysitter);

        Babysitter foundBabysitter = babysitterRepository.findById(babysitter.getId());

        assertNotNull(foundBabysitter);
        assertEquals(babysitter.getId(), foundBabysitter.getId());
    }

    @Test
    public void findByIdNonExistentBabysitterTest() {
        Babysitter foundBabysitter = babysitterRepository.findById(999L);

        assertNull(foundBabysitter);
    }

    @Test
    public void findAllTest() {
        List<Babysitter> babysitters = babysitterRepository.findAll();

        assertNotNull(babysitters);
        assertTrue(babysitters.size() == 0);
    }
}
