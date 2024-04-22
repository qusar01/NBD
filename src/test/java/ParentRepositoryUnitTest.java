import Source.Parent;
import Repositories.ParentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParentRepositoryUnitTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private EntityTransaction transaction;
    private ParentRepository parentRepository;

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
        parentRepository = new ParentRepository(entityManager);
    }

    @AfterEach
    public void rollbackTransaction() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    @Test
    public void savedParentTest() {
        Parent parent = new Parent("Alice", "Kowalska");

        Parent savedParent = parentRepository.save(parent);

        assertNotNull(savedParent.getId());
        assertEquals(parent.getFirstName(), savedParent.getFirstName());
    }

    @Test
    public void changedParentTest() {
        Parent parent = new Parent("Bob", "Marley");
        Parent savedParent = entityManager.merge(parent);

        savedParent.setLastName("Budowniczy");
        Parent updatedParent = parentRepository.save(savedParent);

        assertEquals(savedParent.getId(), updatedParent.getId());
        assertEquals("Budowniczy", updatedParent.getLastName());
    }

    @Test
    public void findByIdTest() {
        Parent parent = new Parent("Carol", "Mandarynka");
        Parent savedParent = entityManager.merge(parent);

        Parent foundParent = parentRepository.findById(savedParent.getId());

        assertNotNull(foundParent);
        assertEquals(savedParent.getId(), foundParent.getId());
    }

    @Test
    public void findByIdNonExistentParent() {
        Parent foundParent = parentRepository.findById(999L);

        assertNull(foundParent);
    }

    @Test
    public void findAllTest() {
        List<Parent> originalList = parentRepository.findAll();

        List<Parent> parents = parentRepository.findAll();

        assertNotNull(parents);
        assertEquals(originalList.size(), parents.size());
    }
}
