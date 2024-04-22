import Source.Babysitter;
import Source.Child;
import Source.Parent;
import Source.Rent;
import Repositories.RentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RentRepositoryUnitTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private EntityTransaction transaction;
    private RentRepository rentRepository;

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
        rentRepository = new RentRepository(entityManager);
    }

    @AfterEach
    public void rollbackTransaction() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    @Test
    public void findByIdTest() {
        Babysitter babysitter = new Babysitter("Anna", 30.0, false);
        Child child = new Child("Tom", 5, "Chłop");
        Parent parent = new Parent("Jan", "Kowalski");
        parent.addChild(child);
        child.addParent(parent);
        Date start = new Date();
        Date end = new Date(start.getTime() + 3600000);
        Rent rent = new Rent(start, end, babysitter, child, parent);
        Rent savedRent = entityManager.merge(rent);

        Rent foundRent = rentRepository.findById(savedRent.getId());

        assertNotNull(foundRent);
        assertEquals(savedRent.getId(), foundRent.getId());
    }

    @Test
    public void findByIdNonExistentRent() {
        Rent foundRent = rentRepository.findById(999L);

        assertNull(foundRent);
    }
}
