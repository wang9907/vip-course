package com.enjoy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

	@Bean(name = "ghDataSource")
	@Primary
	@Autowired
	public DataSource ghDataSource(Environment env) {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		Properties prop = build(env, "spring.datasource.druid.ghDB.");
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName("ghDB");
		ds.setPoolSize(5);
		ds.setXaProperties(prop);
		return ds;

	}

	@Autowired
	@Bean(name = "nhDataSource")
	public AtomikosDataSourceBean businessDataSource(Environment env) {

		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		Properties prop = build(env, "spring.datasource.druid.nhDB.");
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName("nhDB");
		ds.setPoolSize(5);
		ds.setXaProperties(prop);

		return ds;
	}

	@Bean("ghJdbcTemplate")
	public JdbcTemplate sysJdbcTemplate(@Qualifier("ghDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean("nhJdbcTemplate")
	public JdbcTemplate busJdbcTemplate(@Qualifier("nhDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	private Properties build(Environment env, String prefix) {

		Properties prop = new Properties();
		prop.put("url", env.getProperty(prefix + "url"));
		prop.put("username", env.getProperty(prefix + "username"));
		prop.put("password", env.getProperty(prefix + "password"));
		prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
		prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
		prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
		prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
		prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
		prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));

		prop.put("maxPoolPreparedStatementPerConnectionSize",
				env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));

		prop.put("maxPoolPreparedStatementPerConnectionSize",
				env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
		prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
		prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
		prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
		prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
		prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
		prop.put("timeBetweenEvictionRunsMillis",
				env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
		prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
		prop.put("filters", env.getProperty(prefix + "filters"));

		return prop;
	}

}
