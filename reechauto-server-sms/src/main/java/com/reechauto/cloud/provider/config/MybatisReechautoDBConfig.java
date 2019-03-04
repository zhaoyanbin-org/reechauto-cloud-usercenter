package com.reechauto.cloud.provider.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = "com.reechauto.cloud.provider.mapper",sqlSessionFactoryRef="reechautoSqlSessionFactory")
public class MybatisReechautoDBConfig {

	//private static final Logger logger = LoggerFactory.getLogger(MybatisReechautoDBConfig.class);
	private static final String TYPE_ALIASES_PACKAGE = "com.reechauto.cloud.provider.model";
	private static final String MAPPER_LOCATION = "classpath:/mybatis/mapper/*.xml";
	private static final String CONFIG_LOCATION = "classpath:/mybatis/mybatis-config.xml";
	
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
    
	@Primary
	@Bean(name = "reechAutoDatasource")
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

	/*@Primary
	@Bean(name = "reechAutoDatasource")
	@ConfigurationProperties(prefix = "spring.datasource.reechauto")
	public DataSource createDataSource() {
		logger.info("create reechAutoDataSource ...");
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
		 //return DataSourceBuilder.create().build();
	}*/

	@Primary
	@Bean(name = "reechautoSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("reechAutoDatasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
		//sqlSessionFactoryBean.setTypeAliasesPackage("com.reechauto.cloud.provider.common");
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Primary
	@Bean(name = "reechautoSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("reechautoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	

	@Primary
    @Bean(name = "reechAutoDatasourceTransactionManager")
	public DataSourceTransactionManager txManager(@Qualifier("reechAutoDatasource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name = "reechAutoJdbcTemplate")
    public JdbcTemplate createJdbcTemplate( @Qualifier("reechAutoDatasource") DataSource dataSource) {
      return new JdbcTemplate(dataSource);
    }
}
