package dk.freecode.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class ServletConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext container) throws ServletException {
        // Create the root Spring application context
        final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        // Manage the lifecycle of the root application context
        final ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
        container.addListener(contextLoaderListener);

        // Create the dispatcher servlet's Spring application context
        final AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherConfig.class);

        // Register and map the dispatcher servlet
        final Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);

        // Make the dispatcher the default servlet (using "/" as per the Servlet 3.0 specification)
        dispatcher.addMapping("/");

        // Enables support for DELETE and PUT request methods with web browsers
        container.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class).addMappingForUrlPatterns(null,
                                                                                                             false,
                                                                                                             "/*");

        // Add Spring Security support
        container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class).addMappingForUrlPatterns(null,
                                                                                                               false,
                                                                                                               "/*");
    }
}
