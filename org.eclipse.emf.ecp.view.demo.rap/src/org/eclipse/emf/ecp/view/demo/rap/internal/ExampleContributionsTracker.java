/*******************************************************************************
 * Copyright (c) 2011, 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.emf.ecp.view.demo.rap.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecp.view.demo.rap.IExampleContribution;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public final class ExampleContributionsTracker extends
ServiceTracker<IExampleContribution, IExampleContribution> {

	private final Map<String, IExampleContribution> contributions;

	ExampleContributionsTracker(BundleContext context) {
		super(context, IExampleContribution.class, null);
		contributions = new HashMap<String, IExampleContribution>();
	}

	@Override
	public IExampleContribution addingService(
		ServiceReference<IExampleContribution> reference) {
		final IExampleContribution contribution = super.addingService(reference);
		final String id = contribution.getId();
		if (contributions.containsKey(id)) {
			throw new IllegalStateException("Duplicate contribution id: " + id); //$NON-NLS-1$
		}
		contributions.put(id, contribution);
		return contribution;
	}

	@Override
	public void removedService(
		ServiceReference<IExampleContribution> reference,
		IExampleContribution service) {
		contributions.remove(service.getId());
		super.removedService(reference, service);
	}

	public IExampleContribution getContributionById(String id) {
		return contributions.get(id);
	}

	public Map<String, IExampleContribution> getAllContributions()
	{
		return contributions;
	}
}
