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
                // Bật CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Tắt CSRF (dùng JWT)
                .csrf(csrf -> csrf.disable())

                // Stateless
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // ==================== PUBLIC ====================
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/api/images/**",
                                "/api/public/**"
                        ).permitAll()

                        // ==================== USER & ADMIN (GET) ====================
                        .requestMatchers(HttpMethod.GET,
                                "/api/comments/**",
                                "/api/tasks/**",
                                "/api/stories/**",
                                "/api/chapters/**",
                                "/api/favorites/**",
                                "/api/emotions/**",
                                "/api/music/**",
                                "/api/story-music/**",
                                "/api/users/**",
                                "/api/unlock-chapters/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // ==================== USER & ADMIN (POST) ====================
                        .requestMatchers(HttpMethod.POST,
                                "/api/comments/**",
                                "/api/tasks/**",
                                "/api/favorites/**",
                                "/api/unlock-chapters/**",
                                "/api/users/change-password"
                        ).hasAnyRole("USER", "ADMIN")

                        // ==================== USER & ADMIN (DELETE) ====================
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/comments/**",
                                "/api/favorites/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // ==================== ADMIN ONLY ====================
                        // Chỉ áp dụng POST, PUT, DELETE cho các endpoint KHÔNG nằm trong list USER ở trên
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                        // ==================== CÒN LẠI ====================
                        .anyRequest().authenticated()
                )

                // Thêm JWT filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // CORS (dev: cho phép tất cả, production chỉ cần thay "*" thành domain thật)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("*")); // dev
        // production: List.of("https://yourdomain.com", "http://localhost:3000")
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