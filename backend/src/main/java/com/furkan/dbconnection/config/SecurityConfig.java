package com.furkan.dbconnection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    /*@Bean
    public UserDetailsService user(){
        UserDetails user = User.builder().username("riza").password("{bcrypt}$2a$12$GGG5W/JwkPvj3ckAoRJHcOhj2ReAzrqhQK3x27uBktCstiu/l3mWy").roles("USER").build(); //whatwasit
        UserDetails admin = User.builder().username("admin").password("{bcrypt}$2a$12$d9kVs/d12HYRDHgV6IFXwO3pBzNqege8WPsMBcBu/5DJagD5H2Q6y").roles("USER" , "ADMIN").build(); //IamTheAlfa

        return new InMemoryUserDetailsManager(user,admin);s
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}