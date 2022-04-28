package com.webdoodles.tutorials.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
		name = "Webdoodles Logo", 
		description = "This configuration gives you link to logo")
public @interface LogoConfiguration {

	@AttributeDefinition(
			name = "Link to logo", 
			description = "Enter the link")
	public String getLink();
	
}
