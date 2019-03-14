package com.spring.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userService;
	@Autowired
	PersistentTokenRepository persistentTokenRepository;
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userService);
		auth.authenticationProvider(authenticationProvider());
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(userService);
		dap.setPasswordEncoder(passwordEncoder());
		return dap;
	}
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http.authorizeRequests()
		.antMatchers("/","/home","/registration").permitAll()
		.antMatchers("/user/**").access("hasRole('USER')")
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/db/**").access("hasRole('DBA') and hasRole('ADMIN')")
		.antMatchers("/registration/**","/deleteUser/*","/editUser/*","/users").access("hasRole('ADMIN')")
		.and().formLogin().loginPage("/login")
		.usernameParameter("ssoId").passwordParameter("password")
		.and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository).tokenValiditySeconds(3600)
		.and().csrf()
		.and().exceptionHandling().accessDeniedPage("/access_denied");
		
	}
	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenService = new PersistentTokenBasedRememberMeServices("remember-me",userService,persistentTokenRepository);
		return tokenService;
	}
	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
}
