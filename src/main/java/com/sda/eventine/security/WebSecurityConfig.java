package com.sda.eventine.security;

import com.sda.eventine.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/api/user/email/").hasRole("ADMIN")
                 .antMatchers("/api/user/all").hasRole("ADMIN")
                 .antMatchers("/api/**").permitAll()
                 .antMatchers("/resources/**").permitAll()
                 .antMatchers("/").permitAll()
                 .and().formLogin().loginPage("/login").defaultSuccessUrl("/index");

    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder.bCryptPasswordEncoder());
    }


}


