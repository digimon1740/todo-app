package com.kakao.todoapp.core.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDto {

	private Long id;

	private String content;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int ordinal;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime modifiedTime;
}
