package dk.freecode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@ImportResource("/WEB-INF/security-config.xml")
public class SecurityConfig {

    @Bean
    public DelegatingFilterProxy springSecurityFilterChain() {
        return new DelegatingFilterProxy();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);
    }

    @Bean
    public SaltSource saltSource() {
        final ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("userId");
        return saltSource;
    }
}