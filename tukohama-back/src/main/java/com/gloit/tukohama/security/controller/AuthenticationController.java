package com.gloit.tukohama.security.controller;

import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.entity.enumerator.EnumRole;
import com.gloit.tukohama.security.config.CustomUserDetailsService;
import com.gloit.tukohama.security.config.JwtUtil;
import com.gloit.tukohama.security.model.AuthenticationRequest;
import com.gloit.tukohama.security.model.AuthenticationResponse;
import com.gloit.tukohama.security.model.SignupRequest;
import com.gloit.tukohama.service.implementation.RoleServiceImpl;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/auth")
@RestController
public class
AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private RoleServiceImpl roleService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/loadRoles", method = RequestMethod.POST)
	public ResponseEntity<?> loadRoles() throws Exception {
		Role guest=new Role(EnumRole.ROLE_GUEST);
		Role editor=new Role(EnumRole.ROLE_EDITOR);
		Role admin=new Role(EnumRole.ROLE_ADMIN);

		roleService.insert(guest);
		roleService.insert(editor);
		roleService.insert(admin);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);
		Collection<? extends GrantedAuthority> roles = userdetails.getAuthorities();
		Set<String> permisos=new HashSet<>();
		if (roles.contains(new SimpleGrantedAuthority(EnumRole.ROLE_ADMIN.name())))
			permisos.add("admin");
		if (roles.contains(new SimpleGrantedAuthority(EnumRole.ROLE_EDITOR.name())))
			permisos.add("editor");
		if (roles.contains(new SimpleGrantedAuthority(EnumRole.ROLE_GUEST.name())))
			permisos.add("guest");

		return ResponseEntity.ok(new AuthenticationResponse(token, userdetails.getUsername(), permisos));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUser(@RequestBody SignupRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsService.saveInvitado(user));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/registerEditor", method = RequestMethod.PUT)
	public ResponseEntity<?> saveEditor(@RequestBody SignupRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsService.saveEditor(user));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/registerAdmin", method = RequestMethod.PUT)
	public ResponseEntity<?> saveAdmin(@RequestBody SignupRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsService.saveAdmin(user));
	}
	
	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());

		Set<String> permisos=new HashSet<>();
		Boolean admin = claims.get("admin", Boolean.class);
		Boolean editor = claims.get("editor", Boolean.class);
		Boolean guest = claims.get("guest", Boolean.class);

		if (admin != null && admin)
			permisos.add("admin");
		if (editor != null && editor)
			permisos.add("editor");
		if (guest != null && guest)
			permisos.add("guest");

		return ResponseEntity.ok(new AuthenticationResponse(token,expectedMap.get("sub").toString(),permisos));
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}
