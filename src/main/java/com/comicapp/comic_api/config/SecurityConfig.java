    package com.comicapp.comic_api.config;

    import com.comicapp.comic_api.entity.Role;
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
    import org.springframework.web.cors.CorsConfigurationSource;

    @Configuration
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final CorsConfigurationSource corsConfigurationSource;
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http
                    .cors(cors -> cors.configurationSource(corsConfigurationSource))
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth

                            // ===== PUBLIC =====
                            .requestMatchers(
                                    "/auth/login",
                                    "/auth/register",
                                    "/api/images/**",
                                    "/images/**",
                                    "/public/**"
                            ).permitAll()

                            // ===== USER + ADMIN (USER dùng, ADMIN kế thừa) =====
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
                                    "/api/favorites/**"
                            ).hasAnyRole("USER", "ADMIN")

                            .requestMatchers(HttpMethod.DELETE,
                                    "/api/comments/**",
                                    "/api/favorites/**"
                            ).hasAnyRole("USER", "ADMIN")

                            // ===== ADMIN ONLY (toàn quyền) =====
                            .requestMatchers(
                                    HttpMethod.POST,
                                    "/api/**"
                            ).hasRole("ADMIN")

                            .requestMatchers(
                                    HttpMethod.PUT,
                                    "/api/**"
                            ).hasRole("ADMIN")

                            .requestMatchers(
                                    HttpMethod.DELETE,
                                    "/api/**"
                            ).hasRole("ADMIN")

                            // ===== CÒN LẠI =====
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }