package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import dao.ChildDao;
import mappers.ChildMapper;
import mappers.ChildMapperBuilder;
import model.Child;

import java.util.List;
import java.util.UUID;


public class ChildRepository implements IRepository<Child> {

    protected CqlSession session;
    protected ChildMapper childMapper;
    protected ChildDao childDao;

    public ChildRepository(CqlSession session) {
        this.session = session;
        childMapper = new ChildMapperBuilder(session).build();
        childDao = childMapper.childDao();
    }
    @Override
    public Child get(UUID id) {
        return childDao.getById(id);
    }

    @Override
    public void add(Child elements) {
        childDao.createChild(elements);
    }

    @Override
    public void remove(Child elements) {
        childDao.deleteChild(elements);
    }

    @Override
    public void update(Child elements) {
        childDao.updateChild(elements);
    }

    @Override
    public List<Child> find(Object elements) {
        return null;
    }

    @Override
    public List<Child> getAll() {
        return childDao.getAll();
    }
}