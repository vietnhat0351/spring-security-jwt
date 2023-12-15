package com.example.springsecurity.config;

import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        User admin = User.builder()
//                .firstName("Nhat")
//                .lastName("Pham")
//                .password(encoder.encode("123"))
//                .email("admin@gmail.com")
//                .role(Role.ADMIN)
//                .build();
//        User user = User.builder()
//                .firstName("Long")
//                .lastName("Nguyen")
//                .password(encoder.encode("456"))
//                .email("user@gmail.com")
//                .role(Role.USER)
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/api/v1/products/welcome").permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()).build();
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/products/all").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/api/v1/products?id=**").hasAnyAuthority(Role.USER.name())
//                        .requestMatchers(HttpMethod.GET,"/api/v1/products/user").hasAnyAuthority(Role.USER.name())
//                        .requestMatchers(HttpMethod.GET,"/api/v1/products/admin").hasAnyAuthority(Role.ADMIN.name())
                        .anyRequest()
                        .authenticated()
                )
                .
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }

}
