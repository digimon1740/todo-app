package com.kakao.todoapp.core.domain.user;

import org.springframework.util.StringUtils;

public enum Role {
	ROLE_USER, ROLE_ADMIN;

	public static Role of(String name) {
		if (StringUtils.isEmpty(name))
			return Role.ROLE_USER;
		name = name.trim().toUpperCase();
		if (ROLE_ADMIN.name().equals(name)) {
			return Role.ROLE_ADMIN;
		}
		return Role.ROLE_USER;
	}

}
