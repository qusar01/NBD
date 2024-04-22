package Repositories;

import Source.BabysitterType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class BabysitterTypeRepository implements btRepository<BabysitterType> {

    private final EntityManager entityManager;

    public BabysitterTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BabysitterType save(BabysitterType babysitterType) {
        entityManager.getTransaction().begin();
        if(babysitterType.getId() == null) entityManager.persist(babysitterType);
        else babysitterType = entityManager.merge(babysitterType);
        entityManager.getTransaction().commit();
        return babysitterType;
    }

    public BabysitterType findById(Long id) {
        return entityManager.find(BabysitterType.class, id);
    }

    public List<BabysitterType> findAll() {
        Query query = entityManager.createQuery("SELECT bt FROM BabysitterType bt");
        return query.getResultList();
    }
}
