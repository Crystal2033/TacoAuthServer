package com.auth.server.security;

import com.auth.server.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * @project AuthServer
 * ©Crystal2033
 * @date 12/08/2023
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        return httpSecurity.authorizeHttpRequests(authorRequests -> {
                    authorRequests
                            .requestMatchers(mvcMatcherBuilder.pattern("/**")).permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(loginForm -> {

                })
                .csrf(csrf->{csrf.disable();}) //need to show h2-console
                .headers(headers -> { //need to show h2-console
                    headers.frameOptions(options -> {
                        options.disable();
                    });
                })
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
