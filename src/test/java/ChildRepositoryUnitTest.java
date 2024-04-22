import Source.Child;
import Repositories.ChildRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChildRepositoryUnitTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private EntityTransaction transaction;
    private ChildRepository childRepository;

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
        childRepository = new ChildRepository(entityManager);
    }

    @AfterEach
    public void rollbackTransaction() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    @Test
    public void savedChildTest() {
        Child child = new Child("Alice", 5, "babka");

        Child savedChild = childRepository.save(child);

        assertNotNull(savedChild.getId());
        assertEquals(child.getName(), savedChild.getName());
    }

    @Test
    public void changeChildTest() {
        Child child = new Child("Bob", 7, "chłop");
        Child savedChild = entityManager.merge(child);

        savedChild.setAge(8);
        Child updatedChild = childRepository.save(savedChild);

        assertEquals(savedChild.getId(), updatedChild.getId());
        assertEquals(8, updatedChild.getAge());
    }

    @Test
    public void findByIdTest() {
        Child child = new Child("Carol", 6, "chłop");
        transaction.begin();
        Child savedChild = entityManager.merge(child);

        Child foundChild = childRepository.findById(savedChild.getId());

        assertNotNull(foundChild);
        assertEquals(savedChild.getId(), foundChild.getId());
    }

    @Test
    public void findByIdNonExistentChildTest() {
        Child foundChild = childRepository.findById(999L);

        assertNull(foundChild);
    }

    @Test
    public void findAllTest() {
        transaction.begin();
        List<Child> originalList = childRepository.findAll();

        List<Child> children = childRepository.findAll();

        assertNotNull(children);
        assertEquals(originalList.size(), children.size());
    }
}
