/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
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
import java.util.List;

import org.eclipse.emf.forms.main.ExampleUtil;
import org.eclipse.emf.forms.main.IExampleContribution;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
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
		List<ExampleCategory> categories = Examples.getInstance()
				.getCategories();
		for (ExampleCategory category : categories) {
			Button button = new Button(parent, SWT.None);
			button.setData(RWT.CUSTOM_VARIANT, "navigation");
			button.setText(category.getName());
			navigationButtons.add(button);
		}

	}

	protected abstract void selectContribution(IExampleContribution contribution);
}
