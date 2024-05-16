package com.christian.rocketseat.modules.company.useCases;

import javax.naming.AuthenticationException;

import com.auth0.jwt.algorithms.Algorithm;
import com.christian.rocketseat.modules.company.dto.AuthCompanyDTO;
import com.christian.rocketseat.modules.company.dto.AuthCompanyResponseDTO;
import com.christian.rocketseat.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
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

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("christian")
                        .withExpiresAt(expiresIn)
                        .withSubject(company.getId().toString())
                        .withClaim("roles", Arrays.asList("COMPANY"))
                        .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .accessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();
        return authCompanyResponseDTO;
    }
}
