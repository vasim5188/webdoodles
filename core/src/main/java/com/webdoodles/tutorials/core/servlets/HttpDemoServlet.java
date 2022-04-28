package com.webdoodles.tutorials.core.servlets;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webdoodles.tutorials.core.services.HttpDemoService;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=HTTP servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/demo/httpcall" })
public class HttpDemoServlet extends SlingSafeMethodsServlet {
	
	/**
	 * Generated serialVersionUid
	 */
	private static final long serialVersionUID = -2014397651676211439L;
	
	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(HttpDemoServlet.class);
	
	@Reference
	private HttpDemoService httpService;
	
	/**
	 * Overridden doGet() method
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
		try {
		
		String jsonResponse = httpService.makeHttpCall();
		
		/**
		 * Printing the json response on the browser
		 */
		response.getWriter().println(jsonResponse);
		
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
		}
	}

}