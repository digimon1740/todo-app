package com.kakao.todoapp.core.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceService {

	private static final Locale DEFAULT_LOCALE = LocaleContextHolder.getLocale();

	private MessageSource messageSource;

	@Autowired
	public MessageSourceService(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key, Object... args) {
		return messageSource.getMessage(key, args, DEFAULT_LOCALE);
	}

	public String getMessage(String key) {
		return messageSource.getMessage(key, null, DEFAULT_LOCALE);
	}

}
