package com.xproce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain1(HttpSecurity http) throws Exception {
//        // Configuration to deny all the requests
//        http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
//            //This line configures form-based authentication
//            .formLogin(Customizer.withDefaults())
//            //This line configures HTTP Basic authentication
//            .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain2(HttpSecurity http) throws Exception {
//        // Configuration to permit all the requests
//        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())                //This line configures form-based authentication
//            .formLogin(Customizer.withDefaults())
//            //This line configures HTTP Basic authentication
//            .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain3(HttpSecurity http) throws Exception {
//        http.csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests((requests)->requests
//                        .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
//                        .requestMatchers("/notices","/contact","/register").permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    private static final String[] AUTH_WHITELIST = {
            "/notices",
            "/contact",
            "/register",
            "/login"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


}
