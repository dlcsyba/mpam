package cn.sunxingdong.mpam.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/1/20 21:44
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.sunxingdong.mpam")
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    /**
     * 配置JSP视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Bean(name = "/")
    public ParameterizableViewController pvController() {
        ParameterizableViewController pvController = new ParameterizableViewController();
        pvController.setViewName("login");
        return pvController;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

//    @Bean
//    public PropertiesFactoryBean propertiesFactoryBean() {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        Properties props = new Properties();
//        InputStream in = PropertiesFactoryBean.class.getClassLoader().getResourceAsStream("mail.properties");
//        try {
//            props.load(in);
//        } catch (IOException e) {
//            logger.error("fail to load config file", e);
//            return null;
//        }
//        propertiesFactoryBean.setProperties(props);
//        return propertiesFactoryBean;
//    }

//    @Bean
//    public PropertyPlaceholderConfigurer placeholderConfigurer() {
//        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
//        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
//        placeholderConfigurer.setLocations();
//        return placeholderConfigurer;
//    }

    /**
     * 配置邮件发送器
     * @param env
     * @return
     */
    @Bean
    public JavaMailSender mailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mailServer.host"));
        mailSender.setPort(env.getProperty("mailServer.port", Integer.class));
        mailSender.setUsername(env.getProperty("mailServer.username"));
        mailSender.setPassword(env.getProperty("mailServer.password"));
        return mailSender;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/statics/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/statics/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/statics/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/statics/fonts/");
        registry.addResourceHandler("/pages/**").addResourceLocations("/statics/pages/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/statics/plugins/");
    }

    /**
     * 配置静态资源的处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //对静态资源的请求转发到容器缺省的servlet，而不使用DispatcherServlet
        configurer.enable();
    }
}
