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

    @Configuration
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                                    .requestMatchers("/auth/login").permitAll()
                                    .requestMatchers("/auth/register").permitAll()

                            .requestMatchers(
                                    "/images/**",
                                    "/public/**"
                            ).permitAll()
                            .requestMatchers(HttpMethod.GET,
                                    "/api/comments/**",
                                    "/api/tasks/**",
                                    "/api/stories/**",
                                    "/api/chapters/**",
                                    "/api/favorites/**",
                                    "/api/emotions/**",
                                    "/api/music/**",
                                    "/api/story-music/**"
                            ).hasRole("USER")
                            .requestMatchers(HttpMethod.POST,
                                    "/api/comments/**",
                                    "/api/tasks/**",
                                    "/api/favorites/**"
                            ).hasRole("USER")
                            .requestMatchers(HttpMethod.DELETE,
                                    "/api/comments/**",
                                    "/api/favorites/**"
                            ).hasRole("USER")
                            .requestMatchers(
                                    "/api/comments/**",
                                    "/api/tasks/**",
                                    "/api/stories/**",
                                    "/api/chapters/**",
                                    "/api/favorites/**",
                                    "/api/emotions/**",
                                    "/api/music/**",
                                    "/api/story-music/**"
                            ).hasRole("ADMIN")
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