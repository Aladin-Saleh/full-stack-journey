package com.aladin.quizzapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableJpaAuditing 
public class SecurityConfig {


    private JWTFilter jwtFilter;
    private BCryptPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    // private UserServiceImplementation userServiceImplementation;

    public SecurityConfig(JWTFilter jwtFilter, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }
    

    // https://youtu.be/-k1x1EYqlRI?si=m1tfg4_nkhiwV0zi&t=795
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf(AbstractHttpConfigurer::disable) // Desactive la protection CSRF (Cross-Site Request Forgery) en lui passant un objet AbstractHttpConfigurer qui permet de configurer la securite HTTP
        .authorizeHttpRequests(
            authorize -> 
            authorize
            .requestMatchers("/register").permitAll() // Toutes les requetes vers /register et /login sont permises
            .requestMatchers("/login").permitAll()
            .anyRequest().authenticated() // Toutes les autres requetes doivent etre authentifiees
            
            )
            .sessionManagement(
                httpSecuritySessionManagementConfigurer -> 
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // .authenticationProvider(daoAuthenticationProvider())
            .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
        
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    // @Bean
    // public DaoAuthenticationProvider  daoAuthenticationProvider() {
    //     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

    //     daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
    //     daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);

    //     return daoAuthenticationProvider;
    // }
}
