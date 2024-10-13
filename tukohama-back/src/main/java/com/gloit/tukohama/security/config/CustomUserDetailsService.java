package com.gloit.tukohama.security.config;

import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.entity.Usuario;
import com.gloit.tukohama.entity.enumerator.EnumRole;
import com.gloit.tukohama.repository.RoleRepository;
import com.gloit.tukohama.repository.UsuarioRepository;
import com.gloit.tukohama.security.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class
CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
		if (usuario.isPresent()) {
			List<GrantedAuthority> roles = usuario.get().getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().name()))
				.collect(Collectors.toList());
			return new User(usuario.get().getUsername(), usuario.get().getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);	}
	
	public Usuario saveInvitado(SignupRequest signupRequest) {
		Usuario usuario = new Usuario();
		usuario.setUsername(signupRequest.getUsername());
		usuario.setEmail(signupRequest.getUsername());
		usuario.setPassword(bcryptEncoder.encode(signupRequest.getPassword()));
		usuario.setNombre(signupRequest.getNombre());
		usuario.setApellido(signupRequest.getApellido());

		Set<Role> roles=new HashSet<Role>();
		Optional<Role> roleGuest=roleRepository.buscarPorRole(EnumRole.ROLE_GUEST);
		roles.add(roleGuest.get());
		usuario.setRoles(roles);

		return usuarioRepository.save(usuario);
	}

	public Usuario saveEditor(SignupRequest signupRequest) {
		Usuario usuario = new Usuario();
		usuario.setUsername(signupRequest.getUsername());
		usuario.setPassword(bcryptEncoder.encode(signupRequest.getPassword()));
		usuario.setNombre(signupRequest.getNombre());
		usuario.setEmail(signupRequest.getUsername());
		usuario.setApellido(signupRequest.getApellido());
		Set<Role> roles=new HashSet<Role>();
		Optional<Role> roleGuest=roleRepository.buscarPorRole(EnumRole.ROLE_GUEST);
		roles.add(roleGuest.get());
		Optional<Role> roleEditor=roleRepository.buscarPorRole(EnumRole.ROLE_EDITOR);
		roles.add(roleEditor.get());
		usuario.setRoles(roles);
		return usuarioRepository.save(usuario);
	}

	public Usuario saveAdmin(SignupRequest signupRequest) {
		Usuario usuario = new Usuario();
		usuario.setUsername(signupRequest.getUsername());
		usuario.setPassword(bcryptEncoder.encode(signupRequest.getPassword()));
		usuario.setNombre(signupRequest.getNombre());
		usuario.setEmail(signupRequest.getUsername());
		usuario.setApellido(signupRequest.getApellido());
		Set<Role> roles=new HashSet<Role>();
		Optional<Role> roleGuest=roleRepository.buscarPorRole(EnumRole.ROLE_GUEST);
		roles.add(roleGuest.get());
		Optional<Role> roleEditor=roleRepository.buscarPorRole(EnumRole.ROLE_EDITOR);
		roles.add(roleEditor.get());
		Optional<Role> roleAdmin=roleRepository.buscarPorRole(EnumRole.ROLE_ADMIN);
		roles.add(roleAdmin.get());
		usuario.setRoles(roles);
		return usuarioRepository.save(usuario);
	}
}
