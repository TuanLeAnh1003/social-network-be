package com.socialnetwork.security;

import com.socialnetwork.filter.CustomAuthenticationFilter;
import com.socialnetwork.filter.CustomAuthorizationFilter;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        
        http.csrf().disable().cors().configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://effervescent-belekoy-493ea2.netlify.app/", "https://fanciful-kheer-164dc7.netlify.app/"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            configuration.addAllowedHeader("*");
            configuration.setAllowCredentials(true);
            return configuration;
        }).and()
        // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        // .authorizeHttpRequests().antMatchers("/api/login/**", "/api/token/refresh/**", "/api/user/save/**","/socket/**", "/socket/**").permitAll().and()
        //         .authorizeHttpRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ROLE_USER").and()
        //         .authorizeHttpRequests().antMatchers(HttpMethod.GET, "/api/posts/**").hasAnyAuthority("ROLE_USER").and()
        // .authorizeHttpRequests().anyRequest().authenticated().and()
        .authorizeRequests().anyRequest().permitAll().and()
        .addFilter(customAuthenticationFilter)
        .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}