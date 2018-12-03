package com.kakao.todoapp.core.service.user;

import com.kakao.todoapp.web.error.exception.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;

	@Autowired
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	private Authentication getAuthenticationByLoginUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAuthenticated() {
		Authentication authentication = getAuthenticationByLoginUser();
		if (authentication == null) {
			return false;
		}
		return authentication.isAuthenticated();
	}

	public String getAuthenticatedUsername() {
		Authentication auth = getAuthenticationByLoginUser();
		return ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, AccessDeniedException {
		com.kakao.todoapp.core.domain.user.User user = userService.findByName(userName);
		if (user == null) {
			log.info("User not found :  {}", userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		UserDetails userDetails = null;
		try {
			List<GrantedAuthority> grantList = new ArrayList<>();
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
			grantList.add(authority);

			userDetails = new User(user.getName(), user.getPassword(), grantList);
			userService.stampLoginTime(user.getName());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return userDetails;
	}
}