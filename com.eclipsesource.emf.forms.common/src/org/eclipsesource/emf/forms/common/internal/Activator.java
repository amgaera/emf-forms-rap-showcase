package org.eclipsesource.emf.forms.common.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.forms.main.IExampleContribution;
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

	  public void start( BundleContext context ) throws Exception {
	    for( IExampleContribution contribution : contibutions.getContibutions() ) {
	      ServiceRegistration<?> registration;
	      registration = context.registerService( EXAMPLE_CONTRIB, contribution, null );
	      registrations.add( registration );
	    }
	  }

	  public void stop( BundleContext context ) throws Exception {
	    for( ServiceRegistration<?> registration : registrations ) {
	      registration.unregister();
	    }
	  }
}
