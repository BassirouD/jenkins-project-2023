package org.sid.jenkinsservice.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {
}
