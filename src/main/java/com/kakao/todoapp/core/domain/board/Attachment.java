package com.kakao.todoapp.core.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Attachment implements Serializable {

	private String realName;

	private String name;

	private String contentType;

	private long size;
}
