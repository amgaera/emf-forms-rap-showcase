package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

public class BasicApplication implements ApplicationConfiguration {

	@SuppressWarnings("deprecation")
	// TODO: remove depreciation from application
	@Override
	public void configure(Application application) {
		System.out.println("Hello"); //$NON-NLS-1$
		final Map<String, String> properties = new HashMap<String, String>();
		properties.put(WebClient.PAGE_TITLE, "EMF Forms Demos"); //$NON-NLS-1$

		application.addPhaseListener(new DataBindingPhaseListener());
		application.addEntryPoint("/hello", BasicEntryPoint.class, properties); //$NON-NLS-1$
	}

}
