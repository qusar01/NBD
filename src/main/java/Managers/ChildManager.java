package Managers;

import Repositories.RentRepository;
import Repositories.ChildRepository;
import Mapping.ChildMgd;

import java.util.List;

public class ChildManager {
    private final ChildRepository childRepository;
    private final RentRepository rentRepository;

    public ChildManager() {
        this.childRepository = new ChildRepository();
        this.rentRepository = new RentRepository();
    }

    public ChildMgd findByChildName (String name) {
        return childRepository.findByName(name);
    }

    public List<ChildMgd> findAllChildren() {
        return childRepository.findAll();
    }

    public ChildMgd registerChild(ChildMgd child) {
        childRepository.add(child);
        return child;
    }

    public void unregisterChild(ChildMgd child)  {
        childRepository.delete(child.getEntityId().getUUID());
    }
}