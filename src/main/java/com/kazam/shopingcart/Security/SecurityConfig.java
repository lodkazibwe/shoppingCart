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
                .antMatchers("/api/user/addRoot").permitAll()
                .antMatchers("/api/product/all").permitAll()
                .antMatchers("/api/user/addUser").permitAll()
                .antMatchers("/api/user/addAdmin").hasRole("ROOT")
                .antMatchers("/api/user/getUser/{id}").hasRole("ADMIN")
                .antMatchers("/api/product/addProduct").hasRole("ADMIN")
                .antMatchers("/api/product/getProduct/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/order/**").hasAnyRole("ADMIN", "USER")


                .and().formLogin();

    }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }


}
