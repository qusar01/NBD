package Managers;

import Mapping.*;
import Repositories.BabysitterRepository;
import Repositories.RentRepository;

import java.util.List;

public class BabysitterManager {
    private BabysitterRepository babysitterRepository;
    private RentRepository rentRepository;

    public BabysitterManager() {
        this.babysitterRepository = new BabysitterRepository();
        this.rentRepository = new RentRepository();
    }

    public BabysitterMgd getBabysitter(String name) {
        return babysitterRepository.findByName(name);
    }

    public List<BabysitterMgd> findAllBabysitters() {
        return babysitterRepository.findAll();
    }

    public BabysitterMgd registerBabysitter(BabysitterMgd babysitter) {
        if (babysitter.getType() == "Child" || babysitter.getType() == "CHILD" || babysitter.getType() == "child") {
            babysitter = new ChildBabysitterMgd(babysitter.getName(), babysitter.getBasePrice(), babysitter.getIsHired(), babysitter.getType());
            babysitterRepository.add(babysitter);
        } else if (babysitter.getType() == "Infant" || babysitter.getType() == "INFANT" || babysitter.getType() == "infant") {
            babysitter = new InfantBabysitterMgd(babysitter.getName(), babysitter.getBasePrice(), babysitter.getIsHired(), babysitter.getType());
            babysitterRepository.add(babysitter);
        } else if (babysitter.getType() == "Toddler" || babysitter.getType() == "TODDLER" || babysitter.getType() == "toddler") {
            babysitter = new ToddlerBabysitterMgd(babysitter.getName(), babysitter.getBasePrice(), babysitter.getIsHired(), babysitter.getType());
            babysitterRepository.add(babysitter);
        }
        else {
            throw new RuntimeException("Niepoprawny typ klienta");
        }
        return babysitter;
    }

    public void unregisterBabysitter(BabysitterMgd babysitter)  {
        babysitterRepository.delete(babysitter.getEntityId().getUUID());
    }

}
