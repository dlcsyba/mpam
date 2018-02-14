package cn.sunxingdong.mpam.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/1/22 10:23
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class, SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //ָ指定Web配置类
        return new Class[] {AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // 将DispatcherServlet映射到"/"
        return new String[] {"/"};
    }
}
