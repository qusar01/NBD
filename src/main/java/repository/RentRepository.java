package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import dao.RentDao;
import mappers.RentMapper;
import mappers.RentMapperBuilder;
import model.Rent;

import java.util.List;
import java.util.UUID;


public class RentRepository implements IRepository<Rent> {

    protected CqlSession session;
    protected RentMapper rentMapper;
    protected RentDao rentDao;

    public RentRepository(CqlSession session) {
        this.session = session;
        rentMapper = new RentMapperBuilder(session).build();
        rentDao = rentMapper.rentDao();
    }
    @Override
    public Rent get(UUID id) {
        return rentDao.getById(id);
    }

    @Override
    public void add(Rent elements) {
        rentDao.createRent(elements);
    }

    @Override
    public void remove(Rent elements) {
        rentDao.deleteRent(elements);
    }

    @Override
    public void update(Rent elements) {
        rentDao.updateRent(elements);
    }

    @Override
    public List<Rent> find(Object elements) {
        return null;
    }

    @Override
    public List<Rent> getAll() {
        return rentDao.getAll();
    }
}