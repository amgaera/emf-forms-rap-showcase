/*******************************************************************************
 * Copyright (c) 2011, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.emf.ecp.view.demo.rap.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecp.view.demo.rap.IExampleContribution;

public final class Examples {

	private static final List<ExampleCategory> EXAMPLE_CATEGORIES = createCategories();

	private Examples() {
	}

	public static Examples getInstance() {
		return new Examples();
	}

	public List<ExampleCategory> getCategories() {
		return Collections.unmodifiableList(EXAMPLE_CATEGORIES);
	}

	public IExampleContribution getContributionById(String id) {
		return getContributionsTracker().getContributionById(id);
	}

	public Map<String, IExampleContribution> getAllContributions() {
		return getContributionsTracker().getAllContributions();
	}

	public IExampleContribution findInitialContribution() {
		IExampleContribution contribution = null;
		final List<ExampleCategory> categories = getInstance().getCategories();
		if (!categories.isEmpty()) {
			contribution = Examples.getFirstContribution(categories.get(0));
		}
		return contribution;
	}

	private static IExampleContribution getFirstContribution(
		ExampleCategory category) {
		IExampleContribution contribution = null;
		contribution = getInstance().getContributionById(category.getContributionId());
		return contribution;
	}

	private static ExampleContributionsTracker getContributionsTracker() {
		return Activator.getDefault().getExampleContributions();
	}

	private static List<ExampleCategory> createCategories() {
		final List<ExampleCategory> exampleCategories = new ArrayList<ExampleCategory>();
		exampleCategories.add(createCategory("Controls", "button"));
		exampleCategories.add(createCategory("Layouts", "table-template"));
		exampleCategories.add(createCategory("Rules", "rules"));
		exampleCategories.add(createCategory("Custom controls", "custom-controls"));
		exampleCategories.add(createCategory("Overal example", "overal-example"));
		return exampleCategories;
	}

	private static ExampleCategory createCategory(String name, String contributionId) {
		final ExampleCategory exampleCategory = new ExampleCategory(name, contributionId);
		return exampleCategory;
	}
}
