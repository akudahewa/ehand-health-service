package com.zerotoproduction.bucketlist.config;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityManagerFactory",
//        transactionManagerRef = "mySqlTransactionManager",
//        basePackages = {
//                "com.zerotoproduction.bucketlist.repository"})

public class MySqlDBConfig {

//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dbUrl);
//        return new HikariDataSource(config);
//    }



//        @Bean(name = "mysqlDataSource")
//    public DataSource dataSource() {
//            System.out.println("datasource ======================"+dataSource);
//        return dataSource;
//    }

//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        DataSource dataSource = DataSourceBuilder.create().build();
//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< "+dataSource);
//        System.out.println(dataSource);
//        return dataSource;
//    }

//    @Bean(name = "mySqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                            @Qualifier("mysqlDataSource") DataSource dataSource) {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.put("implicit-strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
//        properties.put("physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//        return builder.dataSource(dataSource).properties(properties)
//                .packages("com.zerotoproduction.bucketlist.domain").build();
//    }

//    @Bean(name = "mySqlTransactionManager")
//    public PlatformTransactionManager TransactionManager(
//            @Qualifier("mySqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
//        return new JpaTransactionManager(mysqlEntityManagerFactory);
//    }
}
