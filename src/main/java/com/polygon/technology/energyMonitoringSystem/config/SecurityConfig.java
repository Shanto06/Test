package com.polygon.technology.energyMonitoringSystem.config;

import com.polygon.technology.energyMonitoringSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration class for Spring Security settings in the application.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Configures Spring Security settings for the application.
     *
     * @param http HttpSecurity instance to configure.
     * @return SecurityFilterChain for the application.
     * @throws Exception Exception during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for simplicity.
                .csrf((csrf) -> csrf.disable())
                // Configure CORS policy with defaults.
                .cors(withDefaults())

                .authorizeRequests()
                // Allow access to registration and login endpoints without authentication.
                .requestMatchers("/user/register", "/user/login", "/user/randomPassword", "/groups/create", "/groups/all", "/customers/login",
                        "/customers/all")
                .permitAll()
                // Authorization rules for ADMIN roles.
                .requestMatchers("/user/all", "/user/id/**", "/user/update/**", "/user/resetPassword/**","/user/status/**", "/customers/count",
                        "/user/SearchByNameEmailPhoneNumber", "/admins/create", "/admins/all", "/admins/id/**", "/admins/SearchByNameEmailPhoneNumber/**",
                        "/customers/status/**", "/groups/id/**", "/groups/update/**", "/customers/id/**", "/vendors/create", "/vendors/all", "/vendors/id/*",
                        "/vendors/update/**", "/devices/create", "/devices/all", "/devices/id/**", "/devices/count/**", "/devices/countVendorDevice/**",
                        "/devices/update", "/customers/create")
                .hasAnyAuthority("ADMIN")
                // Require authentication for any other requests.
                .anyRequest()
                .authenticated()

                .and()
                // Configure session management to be STATELESS to use JWT.
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Set the custom authentication provider and JWT filter.
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Build and return the SecurityFilterChain.
        return http.build();
    }
}
