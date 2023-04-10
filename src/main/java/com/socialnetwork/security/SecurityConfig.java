package com.socialnetwork.security;

import com.socialnetwork.filter.CustomAuthenticationFilter;
import com.socialnetwork.filter.CustomAuthorizationFilter;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationManagerBean());
                CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter();
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable().cors().configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(
                    Arrays.asList("http://localhost:4200", "https://moonlit-melomakarona-c885fc.netlify.app/"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            configuration.addAllowedHeader("*");
            configuration.setAllowCredentials(true);
            return configuration;
        }).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/login/**", "/api/token/refresh/**", "/api/user/save/**", "/socket/**", "/socket/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**", "/api/posts", "/api/posts/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/api/user/**", "/api/user/**", "/api/chat/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated().and()
                // .authorizeRequests().anyRequest().permitAll().and()
                .addFilter(customAuthenticationFilter)
                .addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}