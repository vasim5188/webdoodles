package com.webdoodles.tutorials.core.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;

@Component(service=Servlet.class,
property={
        "sling.servlet.methods=POST",
        "sling.servlet.resourceTypes="+ "webdoodles/components/atom/contactus",
        "sling.servlet.extensions=" + "json"
})
public class ContactUsServlet extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String CONTACT_US_DATA_NODE = "/var/webdoodles/contactus";
	public static final String NT_UNSTRUCTURED = "nt:unstructured";
	
	@Override
    protected void doPost(final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("jcr:primaryType", NT_UNSTRUCTURED);
		
		if (name != null) {
			properties.put("name", name);
		}
		
		if (name != null) {
			properties.put("email", email);
		}
		
		if (name != null) {
			properties.put("subject", subject);
		}
		
		if (name != null) {
			properties.put("message", message);
		}
		
		
		
		Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
		
		ResourceResolver resourceResolver = request.getResourceResolver();
		Resource contactUSResource = ResourceUtil.getOrCreateResource(resourceResolver, CONTACT_US_DATA_NODE + "/" + datetime, properties, NT_UNSTRUCTURED, true);
		
		response.getWriter().write(datetime);
	}
	
}
