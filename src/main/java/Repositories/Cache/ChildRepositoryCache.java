package Repositories.Cache;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import Mapping.ChildMgd;
import Repositories.RepositoryDecorator;
import Repositories.RepositoryInterface;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import java.io.File;


public class ChildRepositoryCache extends RepositoryDecorator<ChildMgd> {

    private JedisClientConfig jedisClientConfig;
    private JedisPooled redisClient;
    private final Jsonb jsonb;

    public ChildRepositoryCache(RepositoryInterface<ChildMgd> repository) {
        super.repository = repository;
        setConnection();
        jsonb = JsonbBuilder.create();
    }

    private void setConnection() {
        this.jedisClientConfig = DefaultJedisClientConfig.builder().build();

        try {
            HostAndPort hp;
            hp = readConfig();
            this.redisClient = new JedisPooled(hp, jedisClientConfig);
        } catch (Exception e) {
            this.redisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);
        }
    }

    private HostAndPort readConfig() throws IOException {
        InputStream inputStream = null;
        HostAndPort hp = null;
        try {
            Properties prop = new Properties();
            String propFileName = "/META-INF/config.connection";
            inputStream = getClass().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new IOException();
            }

            String server = prop.getProperty("server");
            String port = prop.getProperty("port");
            int parseInt = Integer.parseInt(port);

            hp = new HostAndPort(server, parseInt);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return hp;
    }
    public ChildMgd get(UUID id) {
        try {
            String idString = id.toString();
            ChildMgd child = jsonb.fromJson(redisClient.get("child:" + idString.replaceAll("-","")), ChildMgd.class);
            return child;
        } catch (JedisException | NullPointerException e) {
            return super.repository.get(id);
        }
    }

    @Override
    public boolean add(ChildMgd child) {
        try {
            String key = "child:" + child.getEntityId().getUUID().toString().replaceAll("-","");
            redisClient.jsonSet(key, jsonb.toJson(child, ChildMgd.class));
            redisClient.expire(key, 1200);
            return super.repository.add(child);
        } catch (JedisException e) {
            System.out.println("BŁĄD: " + e);
            return super.repository.add(child);
        }
    }

    @Override
    public void update(ChildMgd child) {
        try {
            redisClient.jsonSet("child:" + child.getEntityId().toHexString(), child);
            super.repository.update(child);
        } catch (JedisException e) {
            super.repository.update(child);
        }
    }

    public ChildMgd delete(UUID id) {
        try {
            String idString = id.toString();
            ChildMgd child = get(id);
            redisClient.del("child:" + idString.replaceAll("-",""));
            super.repository.delete(id);
            return child;
        } catch (JedisException e) {
            System.out.println("BŁĄD: " + e);
            ChildMgd child = super.repository.delete(id);
            return child;
        }
    }

    public void flushCache() {
        Jedis jd = new Jedis();
        jd.flushAll();
    }
}
