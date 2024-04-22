import Source.BabysitterType;
import Source.ChildBabysitter;
import Repositories.BabysitterTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BabysitterTypeRepositoryUnitTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private EntityTransaction transaction;
    private BabysitterTypeRepository babysitterTypeRepository;

    @BeforeAll
    public static void setUpEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("babysitter_pu");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @BeforeEach
    public void setUp() {
        transaction = entityManager.getTransaction();
        babysitterTypeRepository = new BabysitterTypeRepository(entityManager);
    }

    @AfterEach
    public void rollbackTransaction() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    @Test
    public void savedTypeTest() {
        BabysitterType babysitterType = new ChildBabysitter();

        BabysitterType savedBabysitterType = babysitterTypeRepository.save(babysitterType);

        assertNotNull(savedBabysitterType.getId());
        assertEquals(babysitterType.getType(), savedBabysitterType.getType());
    }

    @Test
    public void changedTypeTest() {
        BabysitterType babysitterType = new ChildBabysitter();
        BabysitterType savedBabysitterType = entityManager.merge(babysitterType);

        savedBabysitterType.setAdditionalCost(30.0);
        BabysitterType updatedBabysitterType = babysitterTypeRepository.save(savedBabysitterType);

        assertEquals(savedBabysitterType.getId(), updatedBabysitterType.getId());
        assertEquals(30.0, updatedBabysitterType.getAdditionalCost());
    }

    @Test
    public void findByIdTest() {
        BabysitterType babysitterType = new ChildBabysitter();
        transaction.begin();
        BabysitterType savedBabysitterType = entityManager.merge(babysitterType);

        BabysitterType foundBabysitterType = babysitterTypeRepository.findById(savedBabysitterType.getId());

        assertNotNull(foundBabysitterType);
        assertEquals(savedBabysitterType.getId(), foundBabysitterType.getId());
    }

    @Test
    public void findByIdNonExistingTypeTest() {
        BabysitterType foundBabysitterType = babysitterTypeRepository.findById(999L);

        assertNull(foundBabysitterType);
    }

    @Test
    public void findAllTest() {
        List<BabysitterType> originalList = babysitterTypeRepository.findAll();

        List<BabysitterType> babysitterTypes = babysitterTypeRepository.findAll();

        assertNotNull(babysitterTypes);
        assertEquals(originalList.size(), babysitterTypes.size());
    }
}
