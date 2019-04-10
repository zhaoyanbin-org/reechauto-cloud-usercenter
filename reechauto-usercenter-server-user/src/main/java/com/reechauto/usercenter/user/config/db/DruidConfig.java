package com.reechauto.usercenter.user.config.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableTransactionManagement
public class DruidConfig {
	@Value("${spring.datasource.reechauto.url}")
    private String dbUrl;
 
    @Value("${spring.datasource.reechauto.username}")
    private String username;
 
    @Value("${spring.datasource.reechauto.password}")
    private String password;
 
    @Value("${spring.datasource.reechauto.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.datasource.reechauto.initialSize}")
    private int initialSize;
 
    @Value("${spring.datasource.reechauto.minIdle}")
    private int minIdle;
 
    @Value("${spring.datasource.reechauto.maxActive}")
    private int maxActive;
 
    @Value("${spring.datasource.reechauto.maxWait}")
    private int maxWait;
 
    @Value("${spring.datasource.reechauto.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
 
    @Value("${spring.datasource.reechauto.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
 
    @Value("${spring.datasource.reechauto.validationQuery}")
    private String validationQuery;
 
    @Value("${spring.datasource.reechauto.testWhileIdle}")
    private boolean testWhileIdle;
 
    @Value("${spring.datasource.reechauto.testOnBorrow}")
    private boolean testOnBorrow;
 
    @Value("${spring.datasource.reechauto.testOnReturn}")
    private boolean testOnReturn;
 
    @Value("${spring.datasource.reechauto.poolPreparedStatements}")
    private boolean poolPreparedStatements;
 
    @Value("${spring.datasource.reechauto.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
 
    @Value("${spring.datasource.reechauto.filters}")
    private String filters;
 
    @Value("{spring.datasource.reechauto.connectionProperties}")
    private String connectionProperties;
    
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidServlet() {
		ServletRegistrationBean<StatViewServlet> reg = new ServletRegistrationBean<StatViewServlet>();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", "admin");
		reg.addInitParameter("loginPassword", "admin");
		reg.addInitParameter("logSlowSql", "true");
		return reg;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}
	
	@Primary
	@Bean(name = "datasource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
 
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
 
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
	
	@Primary
	@Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate( @Qualifier("datasource") DataSource dataSource) {
      return new JdbcTemplate(dataSource);
    }

}