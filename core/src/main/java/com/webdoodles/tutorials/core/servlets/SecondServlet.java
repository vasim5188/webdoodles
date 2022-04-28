package com.webdoodles.tutorials.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(
		service = Servlet.class,
		property = {
				"sling.servlet.resourceTypes=webdoodles/components/atom/contactus",
				"sling.servlet.methods=POST"
		})
public class SecondServlet extends SlingAllMethodsServlet{
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("This message is coming second servlet with resource type");
	}
}
