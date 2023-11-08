package com.whiskersandwonders.config;
import com.whiskersandwonders.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserDetailsLoader usersLoader;

    public SecurityConfig(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin((login) -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard"))
                /* Logout configuration */
                .logout((logout) -> logout.logoutSuccessUrl("/login"))  //CHANGED THIS LINE, ADDED LOGOUT
//                .httpBasic(withDefaults())
                .authorizeHttpRequests((requests) -> requests
//                         Pages that require authentication

                        .requestMatchers(
                                "/profile",
                                "/profile/edit",
                                "/profile/edit/delete/{id}",
                                "/pet/review/{fosterId}",
                                "/dashboard/review",
                                "/dashboard/review/delete/{id}",
                                "/browse/pet",
                                "/browse/foster/*/*/*",
                                "/browse/favorite/*",
                                "/dashboard",
                                "/dashboard/send/validation/error"
                                )
                                .authenticated()

                        /* Pages that do not require authentication
                         * anyone can visit the home page, register, login, and browse */

                        .requestMatchers(
                                "",
                                "/",
                                "/landing",
                                "/sign-up",
                                "/login",
                                "/browse",
                                "/browse/*",
                                "/api/test",
                                "/pets/**",
                                "/logout",
                                "/about",
                                "/api/data/default",
                                "/api/data/search",
                                "/api/data/types",
                                "/api/token"
                                ).permitAll()

                        // allow loading of static resources
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/img/**")
                                .permitAll()
                )
        ;
        return http.build();
    }
}
