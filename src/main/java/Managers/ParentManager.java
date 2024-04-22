package Managers;

import Repositories.RentRepository;
import Repositories.ParentRepository;
import Mapping.ParentMgd;

import java.util.List;

public class ParentManager {
    private final ParentRepository parentRepository;
    private final RentRepository rentRepository;

    public ParentManager() {
        this.parentRepository = new ParentRepository();
        this.rentRepository = new RentRepository();
    }

    public ParentMgd findByParentName (String name) {
        return parentRepository.findByName(name);
    }

    public List<ParentMgd> findAllParents() {
        return parentRepository.findAll();
    }

    public ParentMgd registerParent(ParentMgd parent) {
        parentRepository.add(parent);
        return parent;
    }

    public void unregisterParent(ParentMgd parent)  {
        parentRepository.delete(parent.getEntityId().getUUID());
    }
}