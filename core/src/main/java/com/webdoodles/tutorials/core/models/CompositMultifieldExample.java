package com.webdoodles.tutorials.core.models;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CompositMultifieldExample {

	@ValueMapValue
	private String title;
	
	@ValueMapValue
	private String subTitle;

	@ChildResource (name="multifiled")
	Resource childResource;
	
	private String message;
	
	@PostConstruct
	protected void init() {
		message = "";
		Iterator<Resource> childs = childResource.listChildren();
		while (childs.hasNext()) {
			Resource child = childs.next();
			message = message + " : " + child.getValueMap().get("description");
		}
	}

	public String getTitle() {
		return title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public String getMessage() {
		return message;
	}
}
