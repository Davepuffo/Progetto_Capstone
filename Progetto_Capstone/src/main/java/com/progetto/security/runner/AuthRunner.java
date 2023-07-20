package com.progetto.security.runner;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.progetto.security.controller.AuthController;
import com.progetto.security.entity.ERole;
import com.progetto.security.entity.Role;
import com.progetto.security.payload.LoginDto;
import com.progetto.security.payload.RegisterDto;
import com.progetto.security.repository.RoleRepository;
import com.progetto.security.repository.UserRepository;
import com.progetto.security.service.AuthService;


@Component
public class AuthRunner implements ApplicationRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthService authService;

	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	@Autowired
	private AuthController auth;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");

		if (roleRepository.findAll().isEmpty()) {
			setRoleDefault();
		}
		
		if (userRepository.findAll().isEmpty()) {
			Set<String> roles = new HashSet<>();
			roles.add("ADMIN");
			roles.add("USER");
			RegisterDto registerDto = new RegisterDto().builder().name("Davide").lastname("Galli")
					.username("davideg").email("davy@example.com").password("prova1234").roles(roles).build();
			auth.register(registerDto);
		}
//			LoginDto loginDto = new LoginDto().builder().username("davideg").password("prova1234").build();
//			auth.login(loginDto);
//		} else {
//			LoginDto loginDto = new LoginDto().builder().username("davideg").password("prova1234").build();
//			auth.login(loginDto);
//		}
	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);

		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);

		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
	}

}
