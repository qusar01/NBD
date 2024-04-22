package Repositories;

import Source.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ParentRepository implements pRepository<Parent>{

    private final EntityManager entityManager;

    public ParentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Parent save(Parent parent) {
        entityManager.getTransaction().begin();
        if(parent.getId() == null) entityManager.persist(parent);
        else parent = entityManager.merge(parent);
        entityManager.getTransaction().commit();
        return parent;
    }

    @Override
    public Parent findById(Long id) {
        return entityManager.find(Parent.class, id);
    }

    @Override
    public List<Parent> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Parent p");
        return query.getResultList();
    }
}
