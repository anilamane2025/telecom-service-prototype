package com.telecom.tsms.config;

import com.telecom.tsms.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth->auth
                        .requestMatchers(
                                    "/api/auth/login",
                                        "/api/auth/register",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/api/users/me").authenticated()
                        //ADMIN ONLY
                        .requestMatchers(HttpMethod.POST,"/api/plans/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/plans/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/plans/**").hasRole("ADMIN")

                        //Rest Api's allowed for token users
                        .anyRequest()
                        .authenticated()
                ).formLogin(form->form.disable())

                        .addFilterBefore(
                                jwtAuthenticationFilter,
                                UsernamePasswordAuthenticationFilter.class
                        );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}