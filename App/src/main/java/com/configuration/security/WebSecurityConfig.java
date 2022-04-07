package com.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author avinash.a.mishra
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtAppFilter jwtAppFilter;

    @Autowired
    private BasicSecurityEntryPoint basicSecurityEntryPoint;

    private static final String[] ALLOWED_URL = {
            "/swagger-ui/index.html",
            "/swagger-*/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/webjars/**",
            "/h2-database/**",
            "/authenticate",
            "/health",
            "/User**",
            "/"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers(ALLOWED_URL).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);//telling not to create session after login
        http.headers().frameOptions().disable().and().httpBasic().authenticationEntryPoint(basicSecurityEntryPoint);
        http.addFilterBefore(jwtAppFilter, UsernamePasswordAuthenticationFilter.class);


    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**","/webjars/**", "/static/**", "/css/**","/images/**","/static/css/**","/static/js/**", "/js/**", "/static/images/**");
    }

    @Bean
    public PasswordEncoder getPasswprdEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
