package com.kakao.todoapp.core.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakao.todoapp.core.domain.board.Attachments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDto {

	private Long id;

	private String content;

	private Attachments attachments;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int ordinal;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime modifiedTime;
}
