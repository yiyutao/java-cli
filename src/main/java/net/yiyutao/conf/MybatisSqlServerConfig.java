package net.yiyutao.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: masterYI
 * @date: 2017/10/18
 * @time: 14:56
 * Description:mybatis数据源配置为sqlServer
 */
@Configuration
@MapperScan(basePackages = {"com.ydp.oms.archive.sqlservermapper","com.ydp.oms.movie.sqlservermapper"}, sqlSessionFactoryRef = "sqlServerSqlSessionFactory")
public class MybatisSqlServerConfig {

    @Autowired
    @Qualifier("sqlServerDataSource")
    private DataSource sqlServerDataSource;


    @Bean
    public SqlSessionFactory sqlServerSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(sqlServerDataSource);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlServerSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlServerSqlSessionFactory());
        return template;
    }
}
