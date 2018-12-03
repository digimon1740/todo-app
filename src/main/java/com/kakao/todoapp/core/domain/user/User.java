package com.kakao.todoapp.core.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kakao.todoapp.core.domain.board.Board;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"password", "role"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany
	private List<Board> boards;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role = Role.ROLE_USER;

	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;

	@Column(name = "reg_time")
	private LocalDateTime regTime;

	public User(String name, String password, Role role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}

}
