package com.julien_roux.jug.quickies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.julien_roux.jug.quickies.security.UserService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		http./*addFilterBefore(filter, CsrfFilter.class).*/exceptionHandling().accessDeniedPage("/403")
			.and()
		            .authorizeRequests().anyRequest().permitAll()
		    .and()
		    		.formLogin().loginPage("/login").permitAll()
		    .and()
		    	.logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
		// .passwordEncoder(new ShaPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}
}
