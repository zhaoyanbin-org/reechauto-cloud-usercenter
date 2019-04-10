package com.reechauto.usercenter.user.config.db;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.reechauto.usercenter.user.mapper",sqlSessionFactoryRef="sqlSessionFactory")
public class MybatisDBConfig {

	private static final String TYPE_ALIASES_PACKAGE = "com.reechauto.usercenter.user.entity";
	private static final String MAPPER_LOCATION = "classpath:/mybatis/mapper/*.xml";
	private static final String CONFIG_LOCATION = "classpath:/mybatis/mybatis-config.xml";

	@Primary
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("datasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Primary
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	

	@Primary
    @Bean(name = "dataSourceTransactionManager")
	public DataSourceTransactionManager txManager(@Qualifier("datasource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
