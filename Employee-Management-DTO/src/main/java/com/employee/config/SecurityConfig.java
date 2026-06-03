package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//For BCrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    //For own user password
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {//encoder parameter used for bcrypt
		UserDetails admin= User.builder()
								.username("admin")
								.password(encoder.encode("admin123"))//bcrypting with encoder
								.roles("ADMIN")
								.build();
//		For Viewing Password in Console
			//		String encodedPassword = encoder.encode("admin123");
			//		System.out.println("Encoded Password: " + encodedPassword);
		
		//To check BCrypt Property
			//		System.out.println(encoder.encode("admin123"));
			//		System.out.println(encoder.encode("admin123"));
		
		UserDetails user=User.builder()
								.username("user")
								.password(encoder.encode("user123"))
								.roles("USER")
								.build();
		
		return new InMemoryUserDetailsManager(admin,user);
								
	}
    
    //basic code for basic spring security
	
//    @Bean
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity http) throws Exception {
//
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                    .anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	http
    		.csrf(csrf->csrf.disable())
    		.authorizeHttpRequests(auth -> auth
    				
    				 .requestMatchers(HttpMethod.GET,"/employee/**").hasAnyRole("USER","ADMIN")
    				 .requestMatchers(HttpMethod.POST,"/employee/**").hasAnyRole("ADMIN")
    				 .requestMatchers(HttpMethod.PUT,"/employee/**").hasAnyRole("ADMIN")
    				 .requestMatchers(HttpMethod.DELETE,"/employee/**").hasAnyRole("ADMIN")
    				 
    				 .anyRequest()
    				 .authenticated()
    				 )
    		.httpBasic(Customizer.withDefaults());
    			
    				return http.build();
	
    }
}
