package dk.freecode.config;

import java.beans.PropertyVetoException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.h2.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.googlecode.flyway.core.Flyway;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:hibernate.properties", "jdbc.properties" })
public class PersistenceConfig {

    @Inject
    private Environment env;

    @Bean
    public NamingStrategy namingStrategy() {
        return new ImprovedNamingStrategy();
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(Driver.class.getName());
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBuilder localSessionFactoryBuilder() throws PropertyVetoException {
        final LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());

        builder.scanPackages("dk.freecode.**");
        builder.setNamingStrategy(namingStrategy());
        builder.setProperty("hibernate.dialect", H2Dialect.class.getName());
        builder.setProperty("hibernate.format_sql", "true");
        setPropertiesFromEnvironment(builder, "hibernate.show_sql", "hibernate.hbm2ddl.auto");

        return builder;
    }

    private void setPropertiesFromEnvironment(final LocalSessionFactoryBuilder builder, final String... properties) {
        for (final String prop : properties) {
            builder.setProperty(prop, env.getProperty(prop));
        }
    }

    @Bean
    public Flyway flyway() throws PropertyVetoException {
        final Flyway flyway = new Flyway();

        flyway.setDataSource(dataSource());
        flyway.setInitOnMigrate(true);
        flyway.migrate();

        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    public SessionFactory sessionFactory() throws PropertyVetoException {
        return localSessionFactoryBuilder().buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
