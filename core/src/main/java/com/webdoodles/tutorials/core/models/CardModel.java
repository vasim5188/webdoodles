package com.webdoodles.tutorials.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class})
public class CardModel {
	
	@ValueMapValue
	private String fileReference;
	
	@ValueMapValue
	private String cardTitle;
	
	@ValueMapValue
	private String cardText;
	
	@SlingObject
	Resource resource;
	
	private ButtonModel button;
	
	@PostConstruct
	protected void init() {		
		if(resource != null) {
			button = resource.adaptTo(ButtonModel.class);
		}
	}	
	
	public String getFileReference() {
		return fileReference;
	}
	
	public String getCardTitle() {
		return cardTitle;
	}
	
	public String getCardText() {
		return cardText;
	}
	
	public ButtonModel getButton() {
		return button;
	}
}
