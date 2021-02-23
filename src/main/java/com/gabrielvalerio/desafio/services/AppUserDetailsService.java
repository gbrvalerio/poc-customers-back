package com.gabrielvalerio.desafio.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.gabrielvalerio.desafio.model.User;
import com.gabrielvalerio.desafio.repository.UserRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			List<GrantedAuthority> roles = user.roles.stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
					.collect(Collectors.toList());
			return new org.springframework.security.core.userdetails.User(user.username, user.password, roles);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
