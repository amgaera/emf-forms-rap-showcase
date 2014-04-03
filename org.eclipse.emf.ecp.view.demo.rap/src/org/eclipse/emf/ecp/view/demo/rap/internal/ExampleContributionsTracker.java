/*******************************************************************************
 * Copyright (c) 2011, 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.emf.ecp.view.demo.rap.internal;

import java.util.Collection;
import java.util.Collections;
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
		IExampleContribution contribution = super.addingService(reference);
		String title = contribution.getTitle();
		if (contributions.containsKey(title)) {
			throw new IllegalStateException("Duplicate contribution title: " + title);
		}
		contributions.put(title, contribution);
		return contribution;
	}

	@Override
	public void removedService(
			ServiceReference<IExampleContribution> reference,
			IExampleContribution service) {
		contributions.remove(service.getTitle());
		super.removedService(reference, service);
	}

	public Collection<String> getContributionIds() {
		return Collections.unmodifiableCollection(contributions.keySet());
	}

	public IExampleContribution getContribution(String name) {
		return contributions.get(name);
	}
}
