package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import dao.ParentDao;
import mappers.ParentMapper;
import mappers.ParentMapperBuilder;
import model.Parent;

import java.util.List;
import java.util.UUID;


public class ParentRepository implements IRepository<Parent> {

    protected CqlSession session;
    protected ParentMapper parentMapper;
    protected ParentDao parentDao;

    public ParentRepository(CqlSession session) {
        this.session = session;
        parentMapper = new ParentMapperBuilder(session).build();
        parentDao = parentMapper.parentDao();
    }
    @Override
    public Parent get(UUID id) {
        return parentDao.getById(id);
    }

    @Override
    public void add(Parent elements) {
        parentDao.createParent(elements);
    }

    @Override
    public void remove(Parent elements) {
        parentDao.deleteParent(elements);
    }

    @Override
    public void update(Parent elements) {
        parentDao.updateParent(elements);
    }

    @Override
    public List<Parent> find(Object elements) {
        return null;
    }

    @Override
    public List<Parent> getAll() {
        return parentDao.getAll();
    }
}