package com.repl.remoteui;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/**").permitAll()
                .antMatchers("/token/**").permitAll()
                .antMatchers("/profile/**").permitAll()
                .anyRequest().permitAll()
                .and().formLogin().permitAll()
                .and().csrf().disable().httpBasic();
    }
}
