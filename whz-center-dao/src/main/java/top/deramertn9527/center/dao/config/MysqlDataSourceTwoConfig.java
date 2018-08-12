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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * mysql two connection config
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(
        basePackages = "top.deramertn9527.center.dao.repository.mysql.datasourceTwo",
        entityManagerFactoryRef = "twoEntityManagerFactory",
        transactionManagerRef = "twoTransactionManager"
)
public class MysqlDataSourceTwoConfig {

    @Value("${datasource.mysql.driverClass}")
    private String driverClassName;

    @Value("${datasource.mysql.two.url}")
    private String url;

    @Value("${datasource.mysql.two.username}")
    private String username;

    @Value("${datasource.mysql.two.password}")
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
    public DataSource dataSource2() {
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName(driverClassName);
        dataSource2.setUrl(url);
        dataSource2.setUsername(username);
        dataSource2.setPassword(password);
        dataSource2.setInitialSize(initialSize);
        dataSource2.setMinIdle(minIdle);
        dataSource2.setMaxIdle(maxIdle);
        dataSource2.setMaxWait(maxWait);
        dataSource2.setMaxActive(maxActive);
        dataSource2.setTestWhileIdle(testWhileIdle);
        dataSource2.setTestOnBorrow(testOnBorrow);
        dataSource2.setTestOnReturn(testOnReturn);
        dataSource2.setValidationQuery(validationQuery);
        dataSource2.setValidationQueryTimeout(validationInterval);
        dataSource2.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource2.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        return dataSource2;
    }

    @Bean
    public JpaVendorAdapter twoJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory twoEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource2());
        entityManagerFactory.setPackagesToScan("top.deramertn9527.center.domain.mysql.datasourceTwo");
        entityManagerFactory.setJpaVendorAdapter(twoJpaVendorAdapter());

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
    public EntityManager twoEntityManager() {
        return twoEntityManagerFactory().createEntityManager();
    }

    @Bean
    public JpaTransactionManager twoTransactionManager() {
        return new JpaTransactionManager(twoEntityManagerFactory());
    }

}