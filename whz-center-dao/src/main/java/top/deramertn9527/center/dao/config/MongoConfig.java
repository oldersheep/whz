package top.deramertn9527.center.dao.config;

import com.mongodb.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoDB config
 */
@Slf4j
@Configuration
@Data
@EnableMongoRepositories(basePackages = {"top.deramertn9527"})
public class MongoConfig {

    @Value("${datasource.mongodb.host}")
    private String host;

    @Value("${datasource.mongodb.username}")
    private String username;

    @Value("${datasource.mongodb.password}")
    private String password;

    @Value("${datasource.mongodb.dbname}")
    private String dbName;

    @Value("${datasource.mongodb.method}")
    private String type;

    /**
     * 获取 MongoTemplate
     * @return MongoTemplate
     */
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate;
        switch (type) {
            case "development":
                mongoTemplate = mongoDevTemplate();
                break;
            case "production":
                mongoTemplate = mongoProdTemplate();
                break;
            default:
                throw new Exception("Wrong Parameter: Type");
        }
        return mongoTemplate;
    }

    /**
     * 开发、测试环境 获取 MongoTemplate
     *
     * @return MongoTemplate
     */
    private MongoTemplate mongoDevTemplate() {
        try {
            String url = "mongodb://" + username + ":" + password + "@" + host + "/?authMechanism=MONGODB-CR";
            MongoClientURI mongoClientURI = new MongoClientURI(url);
            Mongo mongo = new MongoClient(mongoClientURI);
            return new MongoTemplate(mongo, dbName);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 生产环境 副本集连接 获取 MongoTemplate
     *
     * @return MongoTemplate
     */
    @SuppressWarnings("deprecation")
    private MongoTemplate mongoProdTemplate() throws Exception {
        UserCredentials userCredentials = new UserCredentials(username, password);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo(), dbName, userCredentials);
        return new MongoTemplate(mongoDbFactory);
    }

    /**
     * 获取 Mongo
     *
     * @return Mongo
     */
    @SuppressWarnings("deprecation")
    private Mongo mongo() throws Exception {
        MongoFactoryBean mongoFactoryBean = new MongoFactoryBean();

        String[] hosts = host.split(",");
        int len = hosts.length;
        ServerAddress[] replicaSetSeeds = new ServerAddress[len];
        for (int i = 0; i < len; i++) {
            String h = hosts[i].split(":")[0];
            int port = Integer.parseInt(hosts[i].split(":")[1]);
            replicaSetSeeds[i] = new ServerAddress(h, port);
        }

        // 副本集 集群连接
        mongoFactoryBean.setReplicaSetSeeds(replicaSetSeeds);
        // 写事务
        mongoFactoryBean.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        // mongo 配置信息
        mongoFactoryBean.setMongoOptions(mongoOptions());
        mongoFactoryBean.afterPropertiesSet();
        return mongoFactoryBean.getObject();
    }

    /**
     * mongo config method
     *
     * @return MongoOptions
     */
    @SuppressWarnings("deprecation")
    private MongoOptions mongoOptions() {
        MongoOptions mongoOptions = new MongoOptions();
        mongoOptions.setConnectionsPerHost(200);
        mongoOptions.setThreadsAllowedToBlockForConnectionMultiplier(10);
        mongoOptions.setConnectTimeout(10000);
        mongoOptions.setMaxWaitTime(300000);
        mongoOptions.setSocketKeepAlive(false);
        mongoOptions.setSocketTimeout(0);
        mongoOptions.setW(1);
        mongoOptions.setWtimeout(0);
        mongoOptions.setFsync(true);
        return mongoOptions;
    }
}