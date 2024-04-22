package Repositories;

import Source.Babysitter;
import Source.BabysitterType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class BabysitterRepository implements bRepository<Babysitter> {

    private final EntityManager entityManager;

    public BabysitterRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Babysitter save(Babysitter babysitter) {
        entityManager.getTransaction().begin();
        if(babysitter.getId() == null) entityManager.persist(babysitter);
        else babysitter = entityManager.merge(babysitter);
        entityManager.getTransaction().commit();
        return babysitter;
    }

    @Override
    public Babysitter hire(Long id) {
        Babysitter babysitter = entityManager.find(Babysitter.class, id);
        if(babysitter != null) {
            babysitter.setHired(true);
            entityManager.merge(babysitter);
        } else {
        }
        return babysitter;
    }

    @Override
    public Babysitter changeType(Long id, BabysitterType babysitterType) {
        Babysitter babysitter = entityManager.find(Babysitter.class, id);
        if(babysitter != null) {
            babysitter.setType(babysitterType);
            entityManager.merge(babysitter);
        } else {
        }
        return babysitter;

    }

    @Override
    public Babysitter findById(Long id) {
        return entityManager.find(Babysitter.class, id);
    }

    @Override
    public List<Babysitter> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Babysitter b");
        return query.getResultList();
    }
}
