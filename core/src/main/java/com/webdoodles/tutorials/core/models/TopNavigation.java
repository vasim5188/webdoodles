package com.webdoodles.tutorials.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TopNavigation {
	@ValueMapValue @Default(values="children")
	private String listFrom;
	
	@ValueMapValue (name="parentPage")
	private String parentPagePath;
	
	@ValueMapValue
	private String[] pages;
	
	@ScriptVariable
    private ResourceResolver resolver;
	
	@ScriptVariable
    private Page currentPage;
	
	private List<Page> navPages;

	private Page parentPage;

	@PostConstruct
    protected void init() {
		navPages = new ArrayList<>();
    	parentPage = currentPage;
    	if( listFrom.equals("children")) {
    		if(parentPagePath != null) {
    			Resource parentPageResource = resolver.getResource(parentPagePath);
    			parentPage = parentPageResource.adaptTo(Page.class);
    			Iterator<Page> children = parentPage.listChildren();
    			
    			while (children.hasNext()) {
					Page navPage = children.next();
					navPages.add(navPage);
				}
    		}
    	} else if(listFrom.equals("static")) {
    		for (String navPagePath : pages) {
    			Resource navPageResource = resolver.getResource(navPagePath);
    			Page navPage = navPageResource.adaptTo(Page.class);
    			navPages.add(navPage);
    		}
    	}
    }
	
	public List<Page> getNavPages() {
		return navPages;
	}
}
