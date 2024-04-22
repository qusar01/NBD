package Repositories;

import Source.Rent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class RentRepository implements rRepository<Rent>{

    private final EntityManager entityManager;

    public RentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Rent save(Rent rent) {
        entityManager.getTransaction().begin();
        if(rent.getId() == null) entityManager.persist(rent);
        else rent = entityManager.merge(rent);
        entityManager.getTransaction().commit();
        return rent;
    }

    @Override
    public Rent findById(Long id) {
        return entityManager.find(Rent.class, id);
    }

    @Override
    public List<Rent> findAll() {
        Query query = entityManager.createQuery("SELECT r FROM Rent r");
        return query.getResultList();
    }
}



