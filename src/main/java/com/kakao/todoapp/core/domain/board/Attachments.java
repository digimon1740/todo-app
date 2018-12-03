package com.kakao.todoapp.core.domain.board;

import com.kakao.todoapp.utils.jackson.JacksonJsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Attachments implements Serializable {

	private List<Attachment> attachments = new ArrayList<>();

	protected Attachments() {
	}

	public Attachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public int size() {
		return attachments.size();
	}

	public boolean isEmpty() {
		return attachments == null || attachments.size() == 0;
	}

	public Attachment findFirstByRealName(String name) {
		return attachments.stream()
			.filter(attachment -> attachment.getRealName().equals(name))
			.findFirst().orElse(null);
	}

	public List<Attachment> toList() {
		return attachments;
	}

	@Override
	public String toString() {
		String value = null;
		try {
			value = JacksonJsonUtils.writeValueAsString(attachments);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return value;
	}

}