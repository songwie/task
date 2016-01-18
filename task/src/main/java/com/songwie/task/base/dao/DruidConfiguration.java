package com.songwie.task.base.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
 
@Configuration
public class DruidConfiguration {
    /*@Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }*/
 
    @Bean(destroyMethod="close")
    @ConfigurationProperties(prefix="datasource.druid")
    @Qualifier("dataSource")
    @Primary //默认数据源 
    public DataSource dataSource() {
    	DruidDataSource source = new DruidDataSource();
    	return source; 
    }
    
    /*@Bean(destroyMethod="close")
    @ConfigurationProperties(prefix="datasource.druid")
    @Qualifier("dataSource2")
    public DataSource dataSource2() {
    	DruidDataSource source = new DruidDataSource();
    	return source; 
    }*/
    
    /*@Bean
    public ServletRegistrationBean statViewServlet() {
    	ServletRegistrationBean reg = new ServletRegistrationBean();
    	reg.setServlet(new StatViewServlet());
    	reg.addUrlMappings("/druid/*");
    	//reg.addInitParameter("allow", druidAllowUrl);
    	//reg.addInitParameter("deny", druidDenyUrl);

    	return reg;
    }
    
    @Bean
    public RequestContextListener requestContextListener(){
        RequestContextListener requestContextListener = new RequestContextListener();

        return requestContextListener;
    }
 
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }*/
    
    /*@Value("${datasource.druid.allow}")
    private String druidAllowUrl;
     
    @Value("${datasource.druid.deny}")
    private String druidDenyUrl;*/

}