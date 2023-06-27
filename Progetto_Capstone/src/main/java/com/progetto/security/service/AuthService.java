package com.progetto.security.service;

import com.progetto.security.payload.LoginDto;
import com.progetto.security.payload.RegisterDto;

public interface AuthService {

	String login(LoginDto loginDto);

	String register(RegisterDto registerDto);

}
