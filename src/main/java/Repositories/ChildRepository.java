package Repositories;

import Source.Child;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ChildRepository implements cRepository<Child> {

    private final EntityManager entityManager;

    public ChildRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Child save(Child child) {
        entityManager.getTransaction().begin();
        if(child.getId() == null) entityManager.persist(child);
        else child = entityManager.merge(child);
        entityManager.getTransaction().commit();
        return child;
    }

    @Override
    public Child findById(Long id) {
        return entityManager.find(Child.class, id);
    }

    @Override
    public List<Child> findAll() {
        Query query = entityManager.createQuery("SELECT c FROM Child c");
        return query.getResultList();
    }
}
