import Managers.RentManager;
import Managers.BabysitterManager;
import Managers.ChildManager;
import Managers.ParentManager;
import Mapping.*;
import Repositories.RentRepository;
import Repositories.BabysitterRepository;
import Repositories.ChildRepository;
import Repositories.ParentRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Tests {

    private RentManager rentManager;
    private BabysitterManager babysitterManager;
    private ChildManager childManager;
    private ParentManager parentManager;
    private BabysitterRepository babysitterRepository;
    private RentRepository rentRepository;
    private ChildRepository childRepository;
    private ParentRepository parentRepository;

    @BeforeAll
    void beforeAll() {
        this.babysitterRepository = new BabysitterRepository();
        this.childRepository = new ChildRepository();
        this.parentRepository = new ParentRepository();
        this.rentRepository = new RentRepository();
    }

    @BeforeEach
    void beforeEach() {
        this.rentManager = new RentManager();
        this.babysitterManager = new BabysitterManager();
        this.childManager = new ChildManager();
        this.parentManager = new ParentManager();
        babysitterRepository.clearDatabase();
        rentRepository.clearDatabase();
        childRepository.clearDatabase();
        parentRepository.clearDatabase();
    }

    @Test
    void addTest(){
        Date end = new Date();
        long currentTimeMillis = end.getTime();
        long newTimeMillis = currentTimeMillis + (60 * 60 * 1000);
        end.setTime(newTimeMillis);
        BabysitterMgd babysitter = new InfantBabysitterMgd("Ania But", 30, 0, "infant");
        babysitterManager.registerBabysitter(babysitter);
        ParentMgd parent = new ParentMgd("Karolina", "Kowalska", null);
        parentManager.registerParent(parent);
        ChildMgd child = new ChildMgd("Brajanek Kowalski", 6, "Chłop", parent);
        childManager.registerChild(child);
        RentMgd rent = new RentMgd(end, babysitter, child, parent);
        rentManager.RentBabysitter(rent);
        assertEquals(1, childManager.findAllChildren().size());
        assertEquals(1, parentManager.findAllParents().size());
        assertEquals(1, babysitterManager.findAllBabysitters().size());
        assertEquals(1, rentManager.findAllCurrentRents().size());
    }

    @Test
    void deleteTest(){
        ParentMgd parent = new ParentMgd("Ania", "Kowalska", null);
        parentManager.registerParent(parent);
        ChildMgd child = new ChildMgd("Maciek", 6, "Chłop", parent);
        childManager.registerChild(child);
        childManager.unregisterChild(child);
        assertEquals(0, childManager.findAllChildren().size());
    }

    @Test
    void updateTest(){
        ParentMgd parent = new ParentMgd("Ania", "Kowalska", null);
        parentManager.registerParent(parent);
        ChildMgd child = new ChildMgd("Maciek", 6, "Chłop", parent);
        childManager.registerChild(child);
        child.setAge(7);
        childRepository.update(child);
        assertEquals(child.getAge(), 7);
    }

    @Test
    void getTest(){
        ParentMgd parent = new ParentMgd("Ania", "Kowalska", null);
        parentManager.registerParent(parent);
        ChildMgd child = new ChildMgd("Maciek", 6, "Chłop", parent);
        childManager.registerChild(child);
        assertEquals(child.getEntityId().getUUID(), childRepository.findByName(child.getName()).getEntityId().getUUID());
    }

    @Test
    void findAllTest(){
        ParentMgd parent = new ParentMgd("Ania", "Kowalska", null);
        parentManager.registerParent(parent);
        ChildMgd child = new ChildMgd("Maciek", 6, "Chłop", parent);
        ChildMgd child1 = new ChildMgd("Maciek1", 6, "Chłop", parent);
        ChildMgd child2 = new ChildMgd("Maciek2", 6, "Chłop", parent);

        childManager.registerChild(child);
        childManager.registerChild(child1);
        childManager.registerChild(child2);

        assertEquals(childManager.findAllChildren().size(), 3);
    }

}