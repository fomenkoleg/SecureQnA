package com.secureqna.secureqna.security;


import com.secureqna.secureqna.security.detailServices.RepositoryUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityRestConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordRestEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordRestEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher((new AntPathRequestMatcher("/api/**")));

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/new/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/supernew/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/all/random/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/all/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/number/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/remove/{id}/**").hasRole("ADMIN");

        //Rest para usuarios coming soon

        // Other endpoints are public
        http.authorizeRequests().anyRequest().permitAll();
        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();
        // Enable Basic Authentication
        http.httpBasic();
        // Disable Form login Authentication
        http.formLogin().disable();
        // Avoid creating session (because every request has credentials)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

}
