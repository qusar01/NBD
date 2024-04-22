package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import dao.BabysitterDao;
import mappers.BabysitterMapper;
import mappers.BabysitterMapperBuilder;
import model.Babysitter;

import java.util.List;
import java.util.UUID;


public class BabysitterRepository implements IRepository<Babysitter> {

    protected CqlSession session;
    protected BabysitterMapper babysitterMapper;
    protected BabysitterDao babysitterDao;

    public BabysitterRepository(CqlSession session) {
        this.session = session;
        babysitterMapper = new BabysitterMapperBuilder(session).build();
        babysitterDao = babysitterMapper.babysitterDao();
    }
    @Override
    public Babysitter get(UUID id) {
        return babysitterDao.getById(id);
    }

    @Override
    public void add(Babysitter elements) {
        babysitterDao.createBabysitter(elements);
    }

    @Override
    public void remove(Babysitter elements) {
        babysitterDao.deleteBabysitter(elements);
    }

    @Override
    public void update(Babysitter elements) {
        babysitterDao.updateBabysitter(elements);
    }

    @Override
    public List<Babysitter> find(Object elements) {
        return null;
    }

    @Override
    public List<Babysitter> getAll() {
        return null;
    }
}