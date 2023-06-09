package com.ivan.isaback.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ivan.isaback.filter.JwtRequestFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	private UserDetailsService userDetailsService;
	private JwtRequestFilter jwtRequestFilter;
	
	public SecurityConfigurer(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		
		http.csrf().disable()
			.authorizeRequests()			
			.antMatchers("/api/auth/generate-token").permitAll()
//			.antMatchers("/api/center/all-pageable").permitAll()
//			.antMatchers("/api/users").permitAll()
//			.antMatchers("/api/appointment").permitAll()
//			.antMatchers("/api/complaint").permitAll()
//			.antMatchers("/api/personnel").permitAll()
//			.antMatchers("/api/questionnaire").permitAll()
//			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
