package br.com.sicredi.system.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	private final MessageSource messageSource;

	public MessageService(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getMessage(String code, String... args) {
		String message = null;
		final Locale currentLocale = LocaleContextHolder.getLocale();
		
		message = this.messageSource.getMessage(code, args, currentLocale);
		message = message.replace("\"", "");
		
		return message;
	}
}
