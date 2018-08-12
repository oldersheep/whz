package top.deramertn9527.center.dao.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * mysql one connection config
 *
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(
        basePackages = "top.deramertn9527.center.dao.repository.mysql.datasourceOne",
        entityManagerFactoryRef = "oneEntityManagerFactory",
        transactionManagerRef = "oneTransactionManager"
)
public class MysqlDataSourceOneConfig {

    @Value("${datasource.mysql.driverClass}")
    private String driverClassName;

    @Value("${datasource.mysql.one.url}")
    private String url;

    @Value("${datasource.mysql.one.username}")
    private String username;

    @Value("${datasource.mysql.one.password}")
    private String password;

    @Value("${datasource.mysql.initialSize}")
    private int initialSize;

    @Value("${datasource.mysql.minIdle}")
    private int minIdle;

    @Value("${datasource.mysql.maxIdle}")
    private int maxIdle;

    @Value("${datasource.mysql.maxWait}")
    private int maxWait;

    @Value("${datasource.mysql.maxActive}")
    private int maxActive;

    @Value("${datasource.mysql.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${datasource.mysql.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${datasource.mysql.testOnReturn}")
    private boolean testOnReturn;

    @Value("${datasource.mysql.validationQuery}")
    private String validationQuery;

    @Value("${datasource.mysql.validationInterval}")
    private int validationInterval;

    @Value("${datasource.mysql.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${datasource.mysql.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${hibernate.show.sql}")
    private boolean showSql;

    @Value("${hibernate.format.sql}")
    private boolean formatSql;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setMaxActive(maxActive);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setValidationQueryTimeout(validationInterval);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        return dataSource;
    }

    @Bean
    public JpaVendorAdapter oneJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory oneEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("top.deramertn9527.center.domain.mysql.datasourceOne");
        entityManagerFactory.setJpaVendorAdapter(oneJpaVendorAdapter());

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.dialect", dialect);

        entityManagerFactory.setJpaProperties(properties);
        entityManagerFactory.afterPropertiesSet();

        return entityManagerFactory.getNativeEntityManagerFactory();
    }

    @Bean
    public JpaTransactionManager oneTransactionManager() {
        return new JpaTransactionManager(oneEntityManagerFactory());
    }
}