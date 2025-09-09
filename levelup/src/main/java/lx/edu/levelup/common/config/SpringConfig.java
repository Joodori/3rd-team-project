package lx.edu.levelup.common.config;

/*
 * DataSource, SqlSession, Mapper
 */

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
@MapperScan(basePackages = "lx.edu.levelup.**.repository")
public class SpringConfig {

	
		@Autowired 
		private org.springframework.core.env.Environment env;
	
		@Bean
		DataSource dataSource() {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(env.getProperty("db.driver"));
			dataSource.setUrl(env.getProperty("db.url"));
			dataSource.setUsername(env.getProperty("db.username"));
			dataSource.setPassword(env.getProperty("db.password"));
			return dataSource;
		}
		
		@Bean
		public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext context) throws Exception {
			SqlSessionFactoryBean fatory = new SqlSessionFactoryBean();
			
			// 1. <property name="dataSource" ref="dataSource" />
			fatory.setDataSource(dataSource());	
			
			// 2. <property name="mapperLocations" value="classpath:mapper-*.xml" />
			fatory.setMapperLocations(context.getResources("classpath*:mapper/*.xml"));			
			// 4. <bean class="org.apache.ibatis.session.Configuration">
			org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

			// 5. <property name="mapUnderscoreToCamelCase" value="true"/>
			configuration.setMapUnderscoreToCamelCase(true);
			
			// 3. <property name="configuration">
			fatory.setConfiguration(configuration);
			return fatory;
		}
 

		@Bean
		public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) { 
			
			// 1. <constructor-arg index="0" ref="sqlSessionFactory"/> 
			SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
			return template;
		}
		
}
