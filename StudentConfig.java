package com.umesh.RoleBasedAuthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class StudentConfig {

      @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


     @Bean
    public UserDetailsService userDetailsService(){

        UserDetails admin= User.builder()
                .username("Umesh")
                .password(passwordEncoder().encode("1234"))
                .roles("Admin")
                .build();

        UserDetails user=User.builder()
                .username("Rahul")
                .password(passwordEncoder().encode("1234"))
                .roles("User")
                .build();
        return new InMemoryUserDetailsManager(admin,user);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.
                         requestMatchers(HttpMethod.GET).hasRole("Admin")
                        .requestMatchers(HttpMethod.POST).hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT).hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE).hasRole("Admin")
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}
