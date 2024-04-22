package Source;

import Repositories.*;
import jakarta.persistence.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("babysitter_pu");
             EntityManager babysitterManager = entityManagerFactory.createEntityManager()) {

            try {
                RentRepository rentRepository = new RentRepository(babysitterManager);
                ParentRepository parentRepository = new ParentRepository(babysitterManager);
                BabysitterRepository babysitterRepository = new BabysitterRepository(babysitterManager);
                BabysitterTypeRepository babysitterTypeRepository = new BabysitterTypeRepository(babysitterManager);
                ChildRepository childRepository = new ChildRepository(babysitterManager);

                Babysitter babysitter = new Babysitter("Anna", 30.0, false);
                BabysitterType type = new ChildBabysitter();
                babysitterTypeRepository.save(type);
                babysitter.setType(type);
                babysitterRepository.save(babysitter);
                BabysitterType type2 = new InfantBabysitter();
                babysitterTypeRepository.save(type2);
                babysitterRepository.changeType(1L, type2);

                Child child = new Child("Tom", 5, "Chłop");
                Parent parent = new Parent("Jan", "Kowalski");
                parent.addChild(child);
                child.addParent(parent);
                parentRepository.save(parent);
                childRepository.save(child);

                Date start = new Date();
                Date end = new Date(start.getTime() + 3600000);

                Rent rent = new Rent(start, end, babysitter, child, parent);
                babysitterRepository.hire(1L);
                rentRepository.save(rent);

                System.out.println(rent.getRentInfo());
            } catch (Exception e) {
                if (babysitterManager.getTransaction().isActive()) {
                    babysitterManager.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }
}