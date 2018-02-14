package cn.sunxingdong.mpam.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 16:34
 */
@Configuration
@PropertySource({"classpath:db.properties", "classpath:mail.properties"})
public class SpringConfig {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer pc(){
        logger.debug("init PropertySourcesPlaceholderConfigurer");
        return new PropertySourcesPlaceholderConfigurer();
    }
}
