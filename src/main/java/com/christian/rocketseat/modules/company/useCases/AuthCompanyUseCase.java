package com.christian.rocketseat.modules.company.useCases;

import com.auth0.jwt.algorithms.Algorithm;
import com.christian.rocketseat.modules.company.dto.AuthCompanyDTO;
import com.christian.rocketseat.modules.company.repositories.CompanyRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO){
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Company not found!");
                }
        );

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if(!passwordMatches) {
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("christian")
                        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                        .withSubject(company.getId().toString())
                        .sign(algorithm);
        return token;
    }
}
