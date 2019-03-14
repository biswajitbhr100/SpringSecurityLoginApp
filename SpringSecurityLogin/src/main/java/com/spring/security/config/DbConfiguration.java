package com.spring.security.config;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.spring.security.config"})
@PropertySource(value = {"classpath:application.properties"})
public class DbConfiguration {
	@Autowired
	private Environment environment;
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.spring.security.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		source.setUrl(environment.getRequiredProperty("jdbc.url"));
		source.setUsername(environment.getRequiredProperty("jdbc.username"));
		source.setPassword(environment.getRequiredProperty("jdbc.password"));
		return source;
	}
	private Properties hibernateProperties() {
		Properties pro = new Properties();
		pro.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		pro.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		pro.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		pro.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		pro.put("javax.persistence.validation.mode", environment.getRequiredProperty("javax.persistence.validation.mode"));
		return pro;
	}
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory session) {
		HibernateTransactionManager txm = new HibernateTransactionManager();
		txm.setSessionFactory(session);
		return txm;
	}
}
