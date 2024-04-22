package Managers;

import Repositories.BabysitterRepository;
import Repositories.ChildRepository;
import Repositories.ParentRepository;
import Repositories.RentRepository;
import Mapping.RentMgd;
import Mapping.BabysitterMgd;

import java.util.List;

public class RentManager {
    private final RentRepository rentRepository;
    private final BabysitterRepository babysitterRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;

    public RentManager() {
        this.rentRepository = new RentRepository();
        this.babysitterRepository = new BabysitterRepository();
        this.childRepository = new ChildRepository();
        this.parentRepository = new ParentRepository();
    }

    public void RentBabysitter(RentMgd rent) {
            rentRepository.add(rent);
            babysitterRepository.update(rent.getBabysitter());
    }

    public void FireBabysitter(RentMgd rent) {
        BabysitterMgd babysitter = rent.getBabysitter();
        rentRepository.delete(rent.getEntityId().getUUID());
        babysitter.setIsHired(0);
        babysitterRepository.update(babysitter);
    }

    public List<RentMgd> findAllCurrentRents() {
        return rentRepository.findAll();
    }


}
