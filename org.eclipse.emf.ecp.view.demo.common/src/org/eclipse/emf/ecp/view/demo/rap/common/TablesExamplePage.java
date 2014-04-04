/*******************************************************************************
 * Copyright (c) 2009, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.emf.ecp.view.demo.rap.common;

import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.demo.rap.ExampleUtil;
import org.eclipse.emf.ecp.view.demo.rap.IExamplePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.eclipsesource.makeithappen.model.task.TaskFactory;
import com.eclipsesource.makeithappen.model.task.User;

public class TablesExamplePage implements IExamplePage {

	@Override
	public void createControl(Composite parent) {
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		parent.setLayout(gridLayout);

		addParagraph(parent,
			"Use the Button widget to create push buttons, toggle buttons, checkboxes and radiobuttons."); //$NON-NLS-1$
		addParagraph(parent, "Push and toggle buttons also support images."); //$NON-NLS-1$

		final User user = TaskFactory.eINSTANCE.createUser();

		try {
			ECPSWTViewRenderer.INSTANCE.render(parent, user);
		} catch (final ECPRendererException e) {
			e.printStackTrace();
		}
	}

	public void addHeading(Composite parent, String text) {
		final Label label = new Label(parent, SWT.NONE);
		label.setText(text.replace("&", "&&")); //$NON-NLS-1$//$NON-NLS-2$
		// label.setData(RWT.CUSTOM_VARIANT, "infobox-heading");
	}

	public void addParagraph(Composite parent, String text) {
		final Label label = new Label(parent, SWT.WRAP);
		label.setText(text);
		label.setLayoutData(ExampleUtil.createFillData());
		// label.setData(RWT.CUSTOM_VARIANT, "infobox");
	}
}
