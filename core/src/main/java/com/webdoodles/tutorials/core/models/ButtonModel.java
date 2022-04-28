package com.webdoodles.tutorials.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ButtonModel {

	@ValueMapValue
	private String buttonLink;
	
	@ValueMapValue  @Default(values="View More")
	private String buttonText;
	
	@ValueMapValue
	private String buttonType;
	
	@ValueMapValue @Named("sling:resourceType")
	private String resourceType;
	
	@PostConstruct
	protected void init() {
		if(buttonLink != null && buttonLink.startsWith("/content")) {
			buttonLink = buttonLink + ".html";
		}
	}

	public String getButtonLink() {
		return buttonLink;
	}

	public String getButtonText() {
		return buttonText;
	}

	public String getButtonType() {
		return buttonType;
	}

	public String getResourceType() {
		return resourceType;
	}
	
}
