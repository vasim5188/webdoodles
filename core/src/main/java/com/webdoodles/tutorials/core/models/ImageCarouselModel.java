package com.webdoodles.tutorials.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageCarouselModel {

	@ChildResource
	Resource images;
	
	private List<String> imagePaths;

	@PostConstruct
	protected void init() {
		imagePaths = new ArrayList<>();
		Iterator<Resource> imageItr = images.listChildren();
		while( imageItr.hasNext()) {
			Resource image = imageItr.next();
			String imagePath = image.getValueMap().get("fileReference", String.class);
			imagePaths.add(imagePath);
		}
	}
	
	public List<String> getImagePaths() {
		return imagePaths;
	}
}
