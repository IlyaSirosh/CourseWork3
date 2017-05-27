package com.sirosh.app.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Illya on 5/27/17.
 */
public class InitApp implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        context.setServletContext(servletContext);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);


        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
