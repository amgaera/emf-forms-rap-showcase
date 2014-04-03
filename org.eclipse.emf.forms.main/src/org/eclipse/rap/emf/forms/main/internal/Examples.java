/*******************************************************************************
 * Copyright (c) 2011, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.emf.forms.main.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.forms.main.IExampleContribution;

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

	public IExampleContribution getContribution(String id) {
		return getContributionsTracker().getContribution(id);
	}

	public IExampleContribution findInitialContribution() {
		IExampleContribution contribution = null;
		List<ExampleCategory> categories = getInstance().getCategories();
		if (!categories.isEmpty()) {
			contribution = Examples.getFirstContribution(categories.get(0));
		}
		return contribution;
	}

	private static IExampleContribution getFirstContribution(
			ExampleCategory category) {
		IExampleContribution contribution = null;
		List<String> contributionIds = category.getContributionIds();
		if (!contributionIds.isEmpty()) {
			contribution = getInstance()
					.getContribution(contributionIds.get(0));
		}
		return contribution;
	}

	private static ExampleContributionsTracker getContributionsTracker() {
		return Activator.getDefault().getExampleContributions();
	}

	// TODO [rst] Read from configuration file
	private static List<ExampleCategory> createCategories() {
		List<ExampleCategory> exampleCategories = new ArrayList<ExampleCategory>();
		exampleCategories.add(createCategory("Control elements", "input",
				"button", "canvas", "dialog"));
		exampleCategories.add(createCategory("Custom elements", "grid-layout",
				"row-layout", "fill-layout"));
		exampleCategories.add(createCategory("Layouts", "tableviewer",
				"treeviewer", "table-markup", "table-template"));
		exampleCategories.add(createCategory("Groups and tables",
				"complex-data", "rich-label", "validation", "drag-and-drop",
				"file-upload", "nls"));
		exampleCategories.add(createCategory("Additional elements", "gmaps",
				"ckeditor", "oscilloscope", "carousel", "chart", "nebula-grid",
				"drop-down"));
		return exampleCategories;
	}

	private static ExampleCategory createCategory(String name,
			String... contributions) {
		ExampleCategory exampleCategory = new ExampleCategory(name);
		for (String contribution : contributions) {
			exampleCategory.addContributionId(contribution);
		}
		return exampleCategory;
	}

}
