package cn.sunxingdong.mpam.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 14:32
 */
@Configuration
@ComponentScan(basePackages = {"cn.sunxingdong.mpam"},
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {

}
