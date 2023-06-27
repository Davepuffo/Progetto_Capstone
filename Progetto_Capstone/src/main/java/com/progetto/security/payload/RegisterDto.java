package com.progetto.security.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
	private String name;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private Set<String> roles;
}
