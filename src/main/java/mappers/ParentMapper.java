package mappers;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import dao.ParentDao;

@Mapper
public interface ParentMapper {
    @DaoFactory
    ParentDao parentDao(@DaoKeyspace String keyspace, @DaoTable String table);

    @DaoFactory
    ParentDao parentDao();
}