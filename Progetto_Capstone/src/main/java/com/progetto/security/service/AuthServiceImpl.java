package com.progetto.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.progetto.security.entity.ERole;
import com.progetto.security.entity.Role;
import com.progetto.security.entity.User;
import com.progetto.security.exception.MyAPIException;
import com.progetto.security.payload.LoginDto;
import com.progetto.security.payload.RegisterDto;
import com.progetto.security.repository.RoleRepository;
import com.progetto.security.repository.UserRepository;
import com.progetto.security.security.JwtTokenProvider;



@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);
		System.out.println(token);
		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {

		// add check for username exists in database
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
		}

		// add check for email exists in database
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setLastname(registerDto.getLastname());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Set<String> roleUtente = registerDto.getRoles();
		Set<Role> roles = new HashSet<>();
		
		for(String role : roleUtente ) {
			ERole e = getRole(role);
			Role r = roleRepository.findByRoleName(e).get();
			roles.add(r);
		}
		user.setRoles(roles);

		if (registerDto.getRoles() == null) {
				Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
				roles.add(userRole);
		}
				
		userRepository.save(user);
		System.out.println(user);

		return "User registered successfully!.";
	}

	public ERole getRole(String role) {
		if (role.equals("ADMIN"))
			return ERole.ROLE_ADMIN;
		else if (role.equals("MODERATOR"))
			return ERole.ROLE_MODERATOR;
		else
			return ERole.ROLE_USER;
	}

}
