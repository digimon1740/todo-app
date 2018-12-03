package com.kakao.todoapp.core.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakao.todoapp.core.domain.board.Board;
import com.kakao.todoapp.core.domain.user.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {

	private long id;

	private List<Board> boards;

	private String name;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Role role = Role.ROLE_USER;

	private LocalDateTime lastLoginTime;
}
