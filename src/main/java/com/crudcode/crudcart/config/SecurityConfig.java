package com.crudcode.crudcart.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crudcode.crudcart.security.JwtFilter;
import com.crudcode.crudcart.security.JwtUtil;
import com.crudcode.crudcart.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authz ->
		authz.requestMatchers(HttpMethod.POST,"/set").permitAll()
		.requestMatchers("/api/users/**").authenticated()
		.requestMatchers("/home").permitAll()
		.anyRequest().permitAll()
		)
		
		
		//.formLogin(form -> form.permitAll().defaultSuccessUrl("/dashboard"))
		.csrf(csrf -> csrf.disable())
		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		
		;
		
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withUsername("poorni")
//				.password(passwordEncoder.encode("poorni"))
//				.roles("USER")
//				.build();
//		UserDetails admin = User.withUsername("arun")
//				.password(passwordEncoder.encode("arun"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user,admin);
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
		

		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
    
	@Bean
	public AuthenticationManager  authenticationManager() {
		return new ProviderManager(
				List.of(authenticationProvider()));
	}
}
