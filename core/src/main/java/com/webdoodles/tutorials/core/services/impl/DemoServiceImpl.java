package com.webdoodles.tutorials.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.webdoodles.tutorials.core.services.DemoService;

@Component(service = DemoService.class, immediate = true)
public class DemoServiceImpl implements DemoService {

	private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Reference
	QueryBuilder queryBuilder;
	
	@Activate
	protected void activate() {
		log.info("================= This message is coming from Demoservice activate method====================");
	}
	
	@Deactivate
	protected void deactivate() {
		log.info("================= This message is coming from Demoservice deactivate method====================");
	}

	@Override
	public List<String> getPagePaths(SlingHttpServletRequest request, Map<String, String> map) {
		List<String> pagePaths = new ArrayList<>();
		ResourceResolver resourceResolver = request.getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);
		
		Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();
		
		List<Hit> hits = result.getHits();
		
		for(Hit hit : hits) {
			try {
				String page = hit.getPath();
				pagePaths.add(page);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pagePaths;
	}
	
}
