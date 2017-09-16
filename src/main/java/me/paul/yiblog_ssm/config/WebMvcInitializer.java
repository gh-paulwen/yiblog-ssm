package me.paul.yiblog_ssm.config;

import me.paul.yiblog_ssm.util.MemcachedUtil;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

/**
 * Created by paul on 17-9-4.
 */
public class WebMvcInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        applicationContext.register(
                WebMvcConfig.class, UtilConfig.class, ServiceConfig.class, DaoConfig.class
        );
        applicationContext.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("yiblog", new DispatcherServlet(applicationContext));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        servletContext.addFilter("contentTypeFilter", characterEncodingFilter);
        servletContext.addListener(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {

            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                MemcachedUtil memcachedUtil = applicationContext.getBean(MemcachedUtil.class);
                memcachedUtil.shutdown();
            }
        });
    }
}
