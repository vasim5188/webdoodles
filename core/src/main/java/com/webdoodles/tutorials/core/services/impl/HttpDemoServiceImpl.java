package com.webdoodles.tutorials.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webdoodles.tutorials.core.services.HttpDemoConfiguration;
import com.webdoodles.tutorials.core.services.HttpDemoService;
import com.webdoodles.tutorials.core.utils.Network;

@Component(service = HttpDemoService.class, immediate = true)
@Designate(ocd = HttpDemoConfiguration.class)
public class HttpDemoServiceImpl implements HttpDemoService {
	
	private static final Logger log = LoggerFactory.getLogger(HttpDemoServiceImpl.class);

	HttpDemoConfiguration configuration;
	
	@Activate
	protected void activate(HttpDemoConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public String makeHttpCall() {

		boolean enable = configuration.enableConfig();
		String protocol = configuration.getProtocol(); // https
		String server = configuration.getServer(); //gorest.co.in
		String endpoint = configuration.getEndpoint(); //public/v2/users
		String name = configuration.getName();
		
		String url = protocol + "://" + server + "/" + endpoint; // https://gorest.co.in/public/v2/users
		
		
		if (enable) {
			String response = Network.readJson(url);
			return response;

		} else {

			log.info("----------< Configuration is not enabled >----------");

			return "Configuration not enabled";
		}
	}

}
