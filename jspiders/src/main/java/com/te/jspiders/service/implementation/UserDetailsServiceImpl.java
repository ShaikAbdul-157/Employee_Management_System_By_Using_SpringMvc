package com.te.jspiders.service.implementation;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Roles;
import com.te.jspiders.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> optionalAU = appUserRepository.findByUsername(username);
		if (optionalAU.isPresent()) {
			AppUser appUser = optionalAU.get();
			Function<Roles, SimpleGrantedAuthority> function = r -> {
				return new SimpleGrantedAuthority(r.getRoleName());

			};
			Set<SimpleGrantedAuthority>authorities=appUser.getRoles().stream().map(function).collect(Collectors.toSet());
		return new User(username, appUser.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("User with the UserName"+username+"doesnot exist");
		
	}

}
