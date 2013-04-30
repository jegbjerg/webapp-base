package dk.freecode.config;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import dk.freecode.support.conversion.StringToTrimmedStringConverter;

@Configuration
@ComponentScan(value = "dk.freecode.web", includeFilters = @Filter(Controller.class))
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * Ensure that we always return UTF-8 when returning text/plain and text/html.
     */
    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", StandardCharsets.UTF_8),
                                                             new MediaType("text", "html", StandardCharsets.UTF_8)));
        converters.add(stringConverter);

        addDefaultHttpMessageConverters(converters);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("favicon.ico").addResourceLocations("/resources/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new StringToTrimmedStringConverter());
    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/ErrorMessages",
                                   "messages/HomeMessages",
                                   "messages/LinksMessages",
                                   "messages/LoginMessages",
                                   "messages/PageMessages",
                                   "messages/SignupMessages",

                                   "messages/admin/MigrationMessages",
                                   "messages/admin/SchemaMessages");
        return messageSource;
    }

    @Bean
    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        final RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
        adapter.setIgnoreDefaultModelOnRedirect(true);
        return adapter;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // Max upload size: 1 MB
        resolver.setMaxUploadSize(1048576);
        resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
        resolver.setResolveLazily(true);
        return resolver;
    }
}
