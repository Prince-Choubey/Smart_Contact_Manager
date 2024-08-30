package com.scm.config;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.impl.SecurityCustommUserDetailService;



@Configuration
public class SecurityConfig {

    // User create and login using java code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user1 =
    // User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN",
    // "USER").build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
    // return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustommUserDetailService userDetailService;

    @Autowired
    private OAuthAythenticationSuccessHandler handler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    // configuration of authentication provider spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // User detail service object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // Password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configuration
        // Urls configure Kiye hai ki konsa url public hai aur private hai
        httpSecurity.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
            // authorize.anyRequest().permitAll();
            // authorize.anyRequest().authenticated();

        });

        // agr hum kuch bhi channge krna hai to hame yaha ayyenge: form login se related

        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password"); 

            
            formLogin.failureHandler(authFailureHandler);


        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        

        // oauth configuations
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            
            oauth.successHandler(handler);
        });


        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");

        });

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
