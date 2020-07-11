package com.kazam.shopingcart.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/api/product/all").permitAll()
                .antMatchers("/addUser").hasRole("ADMIN")
                .antMatchers("/api/product/addProduct").hasRole("ADMIN")
                .antMatchers("/").hasRole("ADMIN")//getProduct/{id}
                .antMatchers("/api/order/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/product/getProduct/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/product/all").hasAnyRole("ADMIN", "USER")

                .and().formLogin();

    }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }


}
