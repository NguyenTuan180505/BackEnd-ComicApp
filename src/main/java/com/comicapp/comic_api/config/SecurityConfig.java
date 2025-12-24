package com.comicapp.comic_api.config;

import com.comicapp.comic_api.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // BẬT CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // TẮT CSRF (vì API dùng JWT)
                .csrf(csrf -> csrf.disable())

                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // ===== PUBLIC =====
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/api/images/**",
                                "/api/public/**"
                        ).permitAll()

                        // ===== USER + ADMIN =====
                        .requestMatchers(HttpMethod.GET,
                                "/api/comments/**",
                                "/api/tasks/**",
                                "/api/stories/**",
                                "/api/chapters/**",
                                "/api/favorites/**",
                                "/api/emotions/**",
                                "/api/music/**",
                                "/api/story-music/**",
                                "/api/users/**"
                        ).hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST,
                                "/api/comments/**",
                                "/api/tasks/**",
                                "/api/favorites/**",
                                "/api/users/change-password"
                        ).hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/comments/**",
                                "/api/favorites/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // ===== ADMIN ONLY =====
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                        // ===== CÒN LẠI =====
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // CẤU HÌNH CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        // 🔥 CHO PHÉP TẤT CẢ — tạm dùng khi dev (sau nên giới hạn domain)
        config.setAllowedOriginPatterns(List.of("*"));

        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
