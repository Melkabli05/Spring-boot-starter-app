package com.starter.app.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static com.starter.app.utils.ProjectConstants.DEFAULT_ENCODING;


@Configuration
public class MessageConfiguration {

	@Bean
	MessageSource generalMessageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/general/GeneralMessages");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);

		return messageSource;
	}

	@Bean
	MessageSource exceptionMessageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/exception/ExceptionMessages");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);

		return messageSource;
	}

	@Bean
	public MessageSource validationMessageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/validation/ValidationMessages");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);

		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {

		final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validationMessageSource());

		return bean;
	}

}
