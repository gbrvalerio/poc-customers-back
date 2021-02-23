package com.gabrielvalerio.desafio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielvalerio.desafio.model.AuthRequest;
import com.gabrielvalerio.desafio.model.AuthResponse;
import com.gabrielvalerio.desafio.model.User;
import com.gabrielvalerio.desafio.repository.UserRepository;
import com.gabrielvalerio.desafio.services.AppUserDetailsService;
import com.gabrielvalerio.desafio.utils.JWTUtils;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private JWTUtils jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(token, new User(userDetails.getUsername(), null, null)));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			final User user = userRepository.findByUsername(username);
			if(user == null || !passwordEncoder.matches(password, user.password)) {
				throw new BadCredentialsException("Usuário e/ou senha inválidos.");
			}
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	

}
