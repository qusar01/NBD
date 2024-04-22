package mappers;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import dao.ChildDao;

@Mapper
public interface ChildMapper {
    @DaoFactory
    ChildDao childDao(@DaoKeyspace String keyspace, @DaoTable String table);

    @DaoFactory
    ChildDao childDao();
}