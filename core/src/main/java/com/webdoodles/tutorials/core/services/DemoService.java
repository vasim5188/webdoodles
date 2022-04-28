package com.webdoodles.tutorials.core.services;

import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;

public interface DemoService {
	List<String> getPagePaths(SlingHttpServletRequest request, Map<String, String> map);
}
