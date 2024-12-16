package com.adv.empdetailsms.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

@Component
public class SwaggerConfig {

	@Bean
	public OperationCustomizer customGlobalHeaders() {
		return (operation, handlerMethod) -> {
			Parameter userId = new Parameter().in(ParameterIn.HEADER.toString()).schema(new StringSchema())
					.name("userId").required(true);
			operation.addParametersItem(userId);
			return operation;
		};
	}
}
