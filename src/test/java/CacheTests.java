import Mapping.ParentMgd;
import Repositories.Cache.ChildRepositoryCache;
import Repositories.ChildRepository;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import Mapping.ChildMgd;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChildRepositoryCacheTest {
    ParentMgd parent = new ParentMgd("Karolina", "Kowalska", null);
    ChildMgd child = new ChildMgd("Brajanek Kowalski", 6, "Chłop", parent);
    UUID id = child.getEntityId().getUUID();
    protected static JedisClientConfig jedisClientConfig;
    protected static Jsonb jsonb;
    protected static JedisPooled redisClient;

    static {
        jedisClientConfig = DefaultJedisClientConfig.builder().build();
        jsonb = JsonbBuilder.create();
        redisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);
    }

    protected static ChildRepositoryCache childRepositoryCache;

    @BeforeAll
    static void setUp() {
        childRepositoryCache = new ChildRepositoryCache(new ChildRepository());
    }
    @BeforeEach
    void beforeEach() {
        childRepositoryCache.flushCache();
    }

    @Test
    void get() {
        assertTrue(childRepositoryCache.add(child));
        ChildMgd cachedChild = childRepositoryCache.get(id);
        assertEquals(cachedChild.getEntityId().getUUID(), id);
    }

    @Test
    void add() {
        assertTrue(childRepositoryCache.add(child));
        assertEquals(childRepositoryCache.get(id).getEntityId().getUUID(), id);
    }

    @Test
    void delete() {
        assertTrue(childRepositoryCache.add(child));
        ChildMgd deletedChild = childRepositoryCache.delete(id);
        assertEquals(deletedChild.getEntityId().getUUID(), child.getEntityId().getUUID());
    }

    @Test
    void flushCache() {
        childRepositoryCache.flushCache();
    }
}
