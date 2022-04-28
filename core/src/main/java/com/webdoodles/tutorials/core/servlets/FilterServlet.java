package com.webdoodles.tutorials.core.servlets;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.gson.Gson;
import com.webdoodles.tutorials.core.bean.Product;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(
        service= Servlet.class,
        property = {"sling.servlet.paths=/bin/webdoodles/filter"}
)
public class FilterServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String tagString = request.getParameter("tag");
        ResourceResolver resourceResolver = request.getResourceResolver();
        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);

        List<Product> productList = new ArrayList<>();

        Tag tag = tagManager.resolve(tagString);
        Iterator<Resource> itr = tag.find();
        while (itr.hasNext()) {
            Resource resource = itr.next();
            Product product = new Product();
            product.setName(resource.getParent().getParent().getName().replace(".jpg",""));
            product.setPath(resource.getPath().replace("/jcr:content/metadata", ""));
            productList.add(product);
        }
        response.getWriter().write(new Gson().toJson(productList));
    }
}
