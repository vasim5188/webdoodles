package com.webdoodles.tutorials.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.gson.Gson;
import com.webdoodles.tutorials.core.services.DemoService;

@Component(
		service=Servlet.class,
		property = {"sling.servlet.paths=/bin/webdoodles/search"}
		)
public class SearchServlet extends SlingSafeMethodsServlet{
	
	@Reference
	DemoService demoService;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String path = request.getParameter("path");
		String propertyname = request.getParameter("property");
		String propertyvalue = request.getParameter("propertyvalue");
		
		Map<String, String> map = new HashMap<>();
		map.put("path", path);
		map.put("type", type);//cq:Page, dam:Asset
		map.put("property", propertyname);
		map.put("property.value", propertyvalue);
		
		List<String> pagePaths = demoService.getPagePaths(request, map);
		
		
		response.getWriter().write(new Gson().toJson(pagePaths));
	}
}
