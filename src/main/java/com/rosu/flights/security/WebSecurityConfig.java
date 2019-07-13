package com.rosu.flights.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper(){
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
    }
 

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// service used to find User in database
		// and encrypt password

		
//		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//links which do not require login
		http
			.authorizeRequests()
			.antMatchers("/", "/login", "/logout")
			.permitAll();
		
		// only if the user is logged in as user or admin
		http
		.authorizeRequests()
		.antMatchers("/userinfo")
		.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		// only if the user is logged in as admin
		http
			.authorizeRequests()
			.antMatchers("/admin")
			.access("hasRole('ROLE_ADMIN')");
		
		// handle form submission
		http.authorizeRequests()
		.and()
		.formLogin()
		.loginProcessingUrl("/login_securely")// submit reuqest
		.loginPage("/login")
		.defaultSuccessUrl("/userinfo")
		.usernameParameter("username")
		.passwordParameter("password")
		//configure logout
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/logoutDone")
		.permitAll();
		
		
	}

}
