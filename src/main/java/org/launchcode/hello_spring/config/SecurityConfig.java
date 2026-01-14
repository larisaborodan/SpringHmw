package org.launchcode.hello_spring.config;

import org.launchcode.hello_spring.data.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authConfig -> {

                    // Public
                    authConfig.requestMatchers(
                            HttpMethod.GET,
                            "/", "/login", "/register", "/error", "/login-error", "/css/**"
                    ).permitAll();

                    authConfig.requestMatchers(HttpMethod.POST, "/createUser").permitAll();

                    // USER pages (DOAR VIEW)
                    authConfig.requestMatchers(HttpMethod.GET,
                            "/user",
                            "/events",
                            "/events/**",
                            "/categories",
                            "/tags"
                    ).hasAnyAuthority("USER", "ADMIN", "DEVELOPER");

                    // ADMIN pages
                    authConfig.requestMatchers("/admin/**").hasAuthority("ADMIN");

                    // Users management
                    authConfig.requestMatchers(HttpMethod.GET, "/users", "/users/**")
                            .hasAnyAuthority("USER", "ADMIN", "DEVELOPER");

                    // CREATE / DELETE (DOAR ADMIN / DEV)
                    authConfig.requestMatchers(HttpMethod.GET, "/events/create").hasAnyAuthority("ADMIN", "DEVELOPER");
                    authConfig.requestMatchers(HttpMethod.POST, "/events/create").hasAnyAuthority("ADMIN", "DEVELOPER");
                    authConfig.requestMatchers("/events/delete/**").hasAuthority("ADMIN");

                    authConfig.requestMatchers("/categories/create/**").hasAuthority("ADMIN");
                    authConfig.requestMatchers("/tags/create/**").hasAuthority("ADMIN");

                    // Restul
                    authConfig.anyRequest().authenticated();

    })
                .formLogin(login -> {
                            login.loginPage("/login");
                            login.defaultSuccessUrl("/");
                            login.failureUrl("/login-error");
                        }
                )

                .logout(logout -> {
                    logout.logoutUrl("/logout"); // POST
                    logout.logoutSuccessUrl("/"); // redirecționează după logout
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                });


        return http.build();
    }

    @Bean
    UserDetailsService myUserDetailsService(UserRepository userRepository) {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successEvent() {
        return event -> {
            System.out.println("Success Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }

    @Bean
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> failureEvent() {
        return event -> {
            System.err.println("Bad Credentials Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }
}
