package com.eric.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-21 11:37
 * @since jdk-11.0.14
 */
public class MybatisConfig
{
    /**
     * 使用SqlSessionFactoryBean封装SqlSessionFactory需要的环境信息
     *
     * @return SqlSessionFactoryBean，用于产生SqlSessionFactory对象
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource)
    {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.eric.domain");
        return sqlSessionFactoryBean;
    }

    /**
     * 使用MapperScannerConfigurer加载Dao接口，创建代理对象保存到IOC容器中
     *
     * @return 用来处理原始配置文件中的mappers相关配置，加载数据层的Mapper接口类
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer()
    {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // basePackage，就是用来设置所扫描的包路径
        mapperScannerConfigurer.setBasePackage("com.eric.dao");
        return mapperScannerConfigurer;
    }
}
