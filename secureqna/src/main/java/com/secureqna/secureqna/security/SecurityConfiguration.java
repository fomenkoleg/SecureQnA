package com.secureqna.secureqna.security;


import com.secureqna.secureqna.security.detailServices.RepositoryUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailService userDetailService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Public pages
        http.requestMatcher((new AntPathRequestMatcher("/secureQnA/**")));
        http.authorizeRequests().antMatchers("/secureQnA/home").permitAll();
        http.authorizeRequests().antMatchers("/secureQnA/home/").permitAll();
        http.authorizeRequests().antMatchers("/secureQnA/login/**").permitAll();
        http.authorizeRequests().antMatchers("/secureQnA/links").permitAll();
        //http.authorizeRequests().antMatchers("/secureQnA/loginError/**").permitAll();
        //http.authorizeRequests().antMatchers("/secureQnA/signUpError/**").permitAll();
        http.authorizeRequests().antMatchers("/secureQnA/signUp/**").permitAll();
        http.authorizeRequests().antMatchers("/secureQnA/user/signUp/verify/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/dropdown/**").permitAll();
        http.authorizeRequests().antMatchers("/assets/**").permitAll();
        http.authorizeRequests().antMatchers("/formoid/**").permitAll();
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        http.authorizeRequests().antMatchers("/mobirise.css/**").permitAll();
        http.authorizeRequests().antMatchers("/parallax/**").permitAll();
        http.authorizeRequests().antMatchers("/playervimeo/**").permitAll();
        http.authorizeRequests().antMatchers("/smoothscroll/**").permitAll();
        http.authorizeRequests().antMatchers("/sociallikes/**").permitAll();
        http.authorizeRequests().antMatchers("/socicon/**").permitAll();
        http.authorizeRequests().antMatchers("/theme/**").permitAll();
        http.authorizeRequests().antMatchers("/web.assets.mobirise-icons2/**").permitAll();
        http.authorizeRequests().antMatchers("/ytplayer/**").permitAll();


        // Private pages (all other pages)
//user, questions, etc.
        http.authorizeRequests().antMatchers("/secureQnA/questionsStart/**").hasRole("USERSQNA");
        http.authorizeRequests().antMatchers("/secureQnA/send/**").hasRole("USERSQNA");
        http.authorizeRequests().antMatchers("/secureQnA/contact/**").hasRole("USERSQNA");
        http.authorizeRequests().antMatchers("/secureQnA/questions/**").hasRole("USERSQNA");
        /*Coming Soon ADMIN
        http.authorizeRequests().antMatchers("/secureQnA/allClients-filtered/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/home/modByAdmin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/allClients/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/client/newByAdmin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/coach/newByAdmin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/newUser-Successful/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/secureQnA/newUser-Error/**").hasRole("ADMIN");
        */
        http.authorizeRequests().anyRequest().authenticated();

//login
        http.formLogin().loginPage("/secureQnA/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("pass");
        http.formLogin().defaultSuccessUrl("/secureQnA/home");
        http.formLogin().failureUrl("/secureQnA/login");

// Logout
        http.logout().logoutUrl("/secureQnA/logout/**")
                .logoutSuccessUrl("/secureQnA/home").permitAll()
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");

    }
}