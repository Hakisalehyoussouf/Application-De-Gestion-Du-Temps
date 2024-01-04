package ma.ensate.projetgestiondutemps.security.configuration;


import com.nimbusds.jose.jwk.source.ImmutableSecret;
import ma.ensate.projetgestiondutemps.security.services.UsersDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {



    @Value("${jwt.secret}")
    private String jwtSecret;
    private UsersDetailsService usersDetailsService;


    public WebSecurityConfiguration(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(usersDetailsService);

        return new ProviderManager(daoAuthenticationProvider);
    }


    @Bean
    JwtEncoder jwtEncoder(){
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtSecret.getBytes()));
    }

    @Bean
    JwtDecoder jwtDecoder(){

        SecretKeySpec secretKeySpec = new SecretKeySpec(this.jwtSecret.getBytes(), "RSA");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar
                        .requestMatchers("/auth/login/**").permitAll()
                        .requestMatchers("/employee/worktime/**").permitAll() //juste pour le test(a supprimer)
                        .requestMatchers("/employee/breaktime/**").permitAll() //juste pour le test(a supprimer)
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
                .build();

    }



}
