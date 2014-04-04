/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecp.view.demo.rap.ExampleUtil;
import org.eclipse.emf.ecp.view.demo.rap.IExampleContribution;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class Navigation {

	private final Composite composite;
	private final List<Button> navigationButtons;

	public Navigation(Composite parent) {
		navigationButtons = new ArrayList<Button>();
		composite = new Composite(parent, SWT.NONE);
		composite
			.setLayout(ExampleUtil.createGridLayoutWithoutMargin(9, false));
		composite.setData(RWT.CUSTOM_VARIANT, "navigation");
		createNavigationControls(composite);
	}

	public Control getControl() {
		return composite;
	}

	private void createNavigationControls(Composite parent) {
		final List<ExampleCategory> categories = Examples.getInstance()
			.getCategories();
		for (final ExampleCategory category : categories) {
			final Button button = new Button(parent, SWT.None);
			button.setData(RWT.CUSTOM_VARIANT, "navigation");
			button.setText(category.getName());
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					final IExampleContribution contribution = Examples.getInstance()
						.getContributionById(category.getContributionId());
					System.out.println("category name: " + category.getName() + " and id: "
						+ category.getContributionId());
					final Map<String, IExampleContribution> allContributions = Examples.getInstance()
						.getAllContributions();
					System.out.println("Key set: " + allContributions.keySet());
					for (final String exampleCategory : allContributions.keySet()) {
						System.out.println("Current key: " + exampleCategory + " and "
							+ allContributions.get(exampleCategory).getId());
					}
					if (contribution != null) {
						buttonSelected(contribution);
						System.out.println("Button selected with id" + contribution.getId());
					} else {
						System.out.println("button selected but contribution is none");
					}
					System.out.println();
				}
			});

			navigationButtons.add(button);
		}

	}

	protected abstract void buttonSelected(IExampleContribution contribution);
}
