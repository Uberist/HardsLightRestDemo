package org.bandana.resthardslightdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
        http.authorizeHttpRequests(c -> c.requestMatchers("/error").permitAll()
                .anyRequest().authenticated());
        http.cors(cors -> {
            var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
            var globalCorsConfiguration = new CorsConfiguration();
            globalCorsConfiguration.addAllowedOrigin("https://bandanaclawa.ru:5173");
        });
        return http.build();
    }
}