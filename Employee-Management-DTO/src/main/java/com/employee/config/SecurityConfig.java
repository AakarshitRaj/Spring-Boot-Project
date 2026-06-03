package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //own user password
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin= User.withDefaultPasswordEncoder()
								.username("admin")
								.password("admin123")
								.roles("ADMIN")
								.build();
		
		UserDetails user=User.withDefaultPasswordEncoder()
								.username("user")
								.password("user123")
								.roles("USER")
								.build();
		
		return new InMemoryUserDetailsManager(admin,user);
								
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
