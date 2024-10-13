package com.gloit.tukohama.security.config;

import com.gloit.tukohama.entity.enumerator.EnumRole;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class
JwtUtil {

	private String secret;
	private int jwtExpirationInMs;
	private int refreshExpirationDateInMs;

	@Value("${tukohama.app.jwtSecret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${tukohama.app.jwtExpirationMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	@Value("${tukohama.app.jwtRefreshExpirationMs}")
	public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
		this.refreshExpirationDateInMs = refreshExpirationDateInMs;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

		if (roles.contains(new SimpleGrantedAuthority(EnumRole.ROLE_GUEST.name())))
			claims.put("guest", true);
		if (roles.contains(new SimpleGrantedAuthority(EnumRole.ROLE_EDITOR.name())))
			claims.put("editor", true);
		if (roles.contains(new SimpleGrantedAuthority(EnumRole.ROLE_ADMIN.name())))
			claims.put("admin", true);

		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}
	
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}

	public List<GrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		List<GrantedAuthority> roles=new ArrayList<GrantedAuthority>();

		Boolean admin = claims.get("admin", Boolean.class);
		Boolean editor = claims.get("editor", Boolean.class);
		Boolean guest = claims.get("guest", Boolean.class);

		if (admin != null && admin)
			roles.add(new SimpleGrantedAuthority(EnumRole.ROLE_ADMIN.name()));
		if (editor != null && editor)
			roles.add(new SimpleGrantedAuthority(EnumRole.ROLE_EDITOR.name()));
		if (guest != null && guest)
			roles.add(new SimpleGrantedAuthority(EnumRole.ROLE_GUEST.name()));

		return roles;

	}

}
