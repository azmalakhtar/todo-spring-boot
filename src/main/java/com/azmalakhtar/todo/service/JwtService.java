package com.azmalakhtar.todo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.JWTClaimsSet;

@Service
public class JwtService {
	private JwtEncoder encoder;

	public JwtService(JwtEncoder encoder) {
		this.encoder = encoder;
	}

	public String generateToken(Authentication authentication) {
		String scope = authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(" "));

		var claims = JwtClaimsSet.builder()
			.issuer("self")
			.issuedAt(Instant.now())
			.expiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
			.subject(authentication.getName())
			.claim("scope", scope)
			.build();

		return this.encoder
			.encode(JwtEncoderParameters.from(claims))
			.getTokenValue();
	}
}
