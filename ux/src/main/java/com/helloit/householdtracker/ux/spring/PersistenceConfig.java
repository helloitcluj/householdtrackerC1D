package com.helloit.householdtracker.ux.spring;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration //mandatory

//Recommended to be able to use @Transactional
@EnableTransactionManagement

//to tell that we use JPA. Argument: where to look for repositories. If not added then it would look in the same package
@EnableJpaRepositories("com.helloit.householdtracker.ux.common.repository")

//config class to handle persistence. could be in AppConfig as well
public class PersistenceConfig {


    public static final String ENTITIES_BASE_PACKAGE = "com.helloit.householdtracker.ux.common.entities";
    public static final String TRUE_TAG = "true";
    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

    private static Properties hibernateProperties(final String hibernateDialect) {
        final Properties properties = new Properties();
        properties.setProperty(PROPERTY_HIBERNATE_DIALECT, hibernateDialect);
        properties.setProperty(PROPERTY_HIBERNATE_SHOW_SQL, TRUE_TAG);
        properties.setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, TRUE_TAG);
        return properties;
    }


    //getting values from app.properties and create dataSource
    @Bean
    public DataSource dataSource(@Value("${driver.classname}") final String driverClassName, @Value("${connection.string}") final String connectionString) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(connectionString);

        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Value("${hibernate.dialect}") final String hibernateDialect, final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource); //data source to use
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class); //set that we want yo use hibernate
        entityManagerFactoryBean.setPackagesToScan(ENTITIES_BASE_PACKAGE); //where to look for entities

        entityManagerFactoryBean.setJpaProperties(hibernateProperties(hibernateDialect));

        return entityManagerFactoryBean;
    }

    //
    @Bean
    public JpaTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean factory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory.getObject());
        return transactionManager;
    }

}
