package ru.leverx.leverxpetsspringbootproxy.config;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.extractor.IasXsuaaExchangeBroker;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import com.sap.cloud.security.xsuaa.tokenflows.XsuaaTokenFlows;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * @author Valeryia Zubrytskaya
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true) // TODO "debug" may include sensitive information. Do not use in a production system!
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecConfig extends WebSecurityConfigurerAdapter {

    private final XsuaaServiceConfiguration xsuaaServiceConfiguration;

    private final XsuaaTokenFlows xsuaaTokenFlows;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/people").hasAuthority("Display")
                .antMatchers(HttpMethod.GET, "/pets").hasAuthority("Display")
                .antMatchers(HttpMethod.GET, "/people/*").hasAuthority("Display")
                .antMatchers(HttpMethod.GET, "/pets/*").hasAuthority("Display")
                .antMatchers("/pets/**")
                .hasAnyAuthority("Add", "Delete", "Edit")
                .antMatchers("/people/**").hasAnyAuthority("Add", "Display", "Delete", "Edit")
                .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer()
                .bearerTokenResolver(new IasXsuaaExchangeBroker(xsuaaTokenFlows))
                .jwt()
                .jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }

    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);
        return converter;
    }
}