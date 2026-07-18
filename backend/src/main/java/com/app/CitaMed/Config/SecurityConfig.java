package com.app.CitaMed.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/health/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/reniec/**").permitAll()
                        .requestMatchers("/api/contacto/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/consultas").permitAll()
                        .requestMatchers("/api/lading/**").permitAll()
                        .requestMatchers("/api/landing/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/api/portal/registro").permitAll()
                        .requestMatchers("/api/portal/recuperar-password").permitAll()
                        .requestMatchers("/api/portal/restablecer-password").permitAll()
                        .requestMatchers("/api/especialidad/**").hasRole("ADMIN")
                        .requestMatchers("/api/medico/**").hasAnyRole("ADMIN","MEDICO")
                        .requestMatchers("/api/horarioMedico/**").hasRole("ADMIN")
                        .requestMatchers("/api/paciente/**").hasAnyRole("ADMIN","MEDICO","RECEPCIONISTA")
                        .requestMatchers("/api/diagnostico/**").hasAnyRole("ADMIN","MEDICO")
                        .requestMatchers("/api/consultorio/**").hasRole("ADMIN")
                        .requestMatchers("/api/empleado/**").hasRole("ADMIN")
                        .requestMatchers("/api/pago/**").hasAnyRole("ADMIN","RECEPCIONISTA")
                        .requestMatchers("/api/historialMedico/**").hasAnyRole("ADMIN","MEDICO","RECEPCIONISTA")
                        .requestMatchers("/api/email/**").hasRole("ADMIN")
                        .requestMatchers("/api/pdf/**").hasAnyRole("ADMIN","MEDICO","RECEPCIONISTA","PACIENTE")
                        .requestMatchers("/api/dashboard/**").hasRole("ADMIN")
                        .requestMatchers("/api/cita/**").hasAnyRole("ADMIN","MEDICO","RECEPCIONISTA")
                        .requestMatchers("/api/usuario/**").hasRole("ADMIN")
                        .requestMatchers("/api/reportes/**").hasRole("ADMIN")
                        .requestMatchers("/api/portal/**").hasRole("PACIENTE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("http://localhost:4200", "https://*.vercel.app"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}