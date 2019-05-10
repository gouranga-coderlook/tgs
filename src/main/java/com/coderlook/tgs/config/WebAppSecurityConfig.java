package com.coderlook.tgs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.coderlook.tgs.constants.TgsConstants;

/**
 * 
 * @author Gouranga
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter
{
   @Autowired
   private UserAuthenticationService authenticationProvider;
   
   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      http.authorizeRequests()
         .antMatchers("/public/**","/forget-password","process", "/master-data/rest/api/**", "/images/**", "/css/**", "/jquery/**","/js/**" ).permitAll()
         .antMatchers("/dashboard","/change-password").access("hasRole('"+TgsConstants.ROLE_USER+"')")
         .antMatchers("/admin/**").access("hasRole('"+TgsConstants.ROLE_ADMIN+"')")
         .antMatchers("/bm/**").access("hasRole('"+TgsConstants.ROLE_BM+"')")
         .antMatchers("/sstg/bm/**").access("hasRole('"+TgsConstants.ROLE_BM+"')")
         .antMatchers("/sstg/asm/**").access("hasRole('"+TgsConstants.ROLE_ASM+"')")
         .antMatchers("/sstg/so/**").access("hasRole('"+TgsConstants.ROLE_SO+"')")
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .permitAll()
         .usernameParameter("username")
         .passwordParameter("password")
         .defaultSuccessUrl("/dashboard", true)
         .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
             req.getSession().setAttribute("message", exp.getMessage());
             res.sendRedirect("/login"); // Redirect user to login page with error message.
          })
         //.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
         .and()
         //.exceptionHandling().accessDeniedPage("/public/accessDenied")
         //.and()
         .logout()
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/public/logout").permitAll();
      
      	  http.sessionManagement().
      	  sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().
      	  sessionManagement().invalidSessionUrl("/login").and().
      	  sessionManagement().sessionFixation().migrateSession();
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder authMgrBuilder)
      throws Exception
   {
      authMgrBuilder.authenticationProvider(authenticationProvider);
   }
   
 
   
}
