package ma.ensate.projetgestiondutemps.security.controllers;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class SecurityController {


    private JwtEncoder jwtEncoder;
    private AuthenticationManager authenticationManager;



    @PostMapping("/login")
    public Map<String, String> login(String email, String password){


        Authentication authentication = this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, password)
        );


        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
                .builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                .subject(email)
                .claim("scope", scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );


        String jwt = this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();


        return Map.of("access-token", jwt);


    }


}
