package com.kakao.todoapp.core.domain.board;

import com.kakao.todoapp.utils.jackson.JacksonJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Converter(autoApply = true)
public class AttachmentsConverter implements AttributeConverter<Attachments, String> {

	@Override
	public String convertToDatabaseColumn(Attachments attachments) {
		if (attachments == null || attachments.size() == 0)
			return null;
		return attachments.toString();
	}

	@Override
	public Attachments convertToEntityAttribute(String input) {
		if (StringUtils.isEmpty(input))
			return null;

		List<Attachment> attachments = new ArrayList<>();
		try {
			List<LinkedHashMap> jsonArray = JacksonJsonUtils.readValue(input, ArrayList.class);
			for (LinkedHashMap json : jsonArray) {
				Attachment attachment = JacksonJsonUtils.readValue(json, Attachment.class);
				attachments.add(attachment);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new Attachments(attachments);
	}

}