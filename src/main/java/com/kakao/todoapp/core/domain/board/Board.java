package com.kakao.todoapp.core.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kakao.todoapp.core.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "boards")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Lob
	@NotNull
	@Column(name = "content")
	private String content;

	@Column(name = "attachments")
	private Attachments attachments;

	@NotNull
	@Column(name = "ordinal")
	private int ordinal;

	@Column(name = "modified_time")
	private LocalDateTime modifiedTime;

	@Column(name = "reg_time")
	private LocalDateTime regTime;
}
