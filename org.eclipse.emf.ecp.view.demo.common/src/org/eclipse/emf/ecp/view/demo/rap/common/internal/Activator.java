package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecp.view.demo.rap.IExampleContribution;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static final String EXAMPLE_CONTRIB = IExampleContribution.class.getName();

	private final Contributions contibutions;
	private final List<ServiceRegistration<?>> registrations;

	public Activator() {
		contibutions = new Contributions();
		registrations = new ArrayList<ServiceRegistration<?>>();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		for (final IExampleContribution contribution : contibutions.getContibutions()) {
			ServiceRegistration<?> registration;
			registration = context.registerService(EXAMPLE_CONTRIB, contribution, null);
			registrations.add(registration);
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		for (final ServiceRegistration<?> registration : registrations) {
			registration.unregister();
		}
	}
}
