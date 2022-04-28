package com.webdoodles.tutorials.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.webdoodles.tutorials.core.services.LogoConfiguration;
import com.webdoodles.tutorials.core.services.LogoService;

@Component(service = LogoService.class)
@Designate(ocd = LogoConfiguration.class)
public class LogoServiceImpl implements LogoService {

	LogoConfiguration configuration;
	
	@Activate
	protected void activate(LogoConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public String getLogoLink() {
		return configuration.getLink();
	}

}
