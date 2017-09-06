package com.mcoding.base.ui.web.context;

import javax.servlet.ServletContext;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.ContextLoaderListener;

public class McodingContextLoaderListener extends ContextLoaderListener {

	@Override
	protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
//		super.configureAndRefreshWebApplicationContext(wac, sc);
		
		if (ObjectUtils.identityToString(wac).equals(wac.getId())) {
			// The application context id is still set to its original default value
			// -> assign a more useful id based on available information
			String idParam = sc.getInitParameter(CONTEXT_ID_PARAM);
			if (idParam != null) {
				wac.setId(idParam);
			}
			else {
				// Generate default id...
				wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX +
						ObjectUtils.getDisplayString(sc.getContextPath()));
			}
		}

		wac.setServletContext(sc);
		String configLocationParam = sc.getInitParameter(CONFIG_LOCATION_PARAM);
//		String fusionConfigLocation = Conf.getFactory().fusionSpringXml(configLocation, "Spring");
		if (configLocationParam != null) {
			wac.setConfigLocation(configLocationParam);
		}

		// The wac environment's #initPropertySources will be called in any case when the context
		// is refreshed; do it eagerly here to ensure servlet property sources are in place for
		// use in any post-processing or initialization that occurs below prior to #refresh
		ConfigurableEnvironment env = wac.getEnvironment();
		if (env instanceof ConfigurableWebEnvironment) {
			((ConfigurableWebEnvironment) env).initPropertySources(sc, null);
		}

		customizeContext(sc, wac);
		wac.refresh();
	}

	/*@Override
	protected WebApplicationContext createWebApplicationContext(ServletContext sc) {
		// TODO Auto-generated method stub
		return super.createWebApplicationContext(sc);
	}*/
	
	
	
	

}
