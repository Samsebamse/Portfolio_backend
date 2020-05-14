package com.portfolio.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserConfig applicationUserConfig;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserConfig applicationUserConfig, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserConfig = applicationUserConfig;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(applicationUserConfig.getName())
                .password(passwordEncoder.encode(applicationUserConfig.getPassword()))
                .authorities(applicationUserConfig.getRoles());
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                    "SELECT username, password, enabled " +
                    "FROM account " +
                    "JOIN account_authority " +
                    "ON account.accountid = account_authority.accountid " +
                    "JOIN authority " +
                    "ON authority.authorityid = account_authority.authorityid " +
                    "WHERE username = ?"
                )
                .authoritiesByUsernameQuery(
                    "SELECT username, type, enabled " +
                    "FROM account " +
                    "JOIN account_authority " +
                    "ON account.accountid = account_authority.accountid " +
                    "JOIN authority " +
                    "ON authority.authorityid = account_authority.authorityid " +
                    "WHERE username = ?"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                //.authenticated()
                .permitAll()
                .and()
                .formLogin();
    }
}
