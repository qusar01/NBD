package mappers;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import dao.BabysitterDao;

@Mapper
public interface BabysitterMapper {
    @DaoFactory
    BabysitterDao babysitterDao(@DaoKeyspace String keyspace, @DaoTable String table);

    @DaoFactory
    BabysitterDao babysitterDao();
}