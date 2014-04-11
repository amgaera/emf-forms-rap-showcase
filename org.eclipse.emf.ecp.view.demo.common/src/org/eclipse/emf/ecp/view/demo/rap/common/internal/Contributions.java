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
package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecp.view.demo.rap.IExampleContribution;
import org.eclipse.emf.ecp.view.demo.rap.IExamplePage;
import org.eclipse.emf.ecp.view.demo.rap.common.ButtonExamplePage;
import org.eclipse.emf.ecp.view.demo.rap.common.LayoutExamplePage;
import org.eclipse.emf.ecp.view.demo.rap.common.OverallExamplePage;
import org.eclipse.emf.ecp.view.demo.rap.common.RulesExamplePage;

class Contributions {

	private final List<IExampleContribution> contributions;

	Contributions() {
		contributions = new ArrayList<IExampleContribution>();
		collectContributions();
	}

	List<IExampleContribution> getContibutions() {
		return Collections.unmodifiableList(contributions);
	}

	private void collectContributions() {
		addContribution("button", "Buttons", ButtonExamplePage.class); //$NON-NLS-1$ //$NON-NLS-2$
		addContribution("layout", "Layouts", LayoutExamplePage.class); //$NON-NLS-1$ //$NON-NLS-2$
		addContribution("rules", "Rules", RulesExamplePage.class); //$NON-NLS-1$ //$NON-NLS-2$
		addContribution("overall-example", "Overall example", OverallExamplePage.class); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void addContribution(final String id,
		final String title,
		final Class<? extends IExamplePage> clazz)
	{
		final IExampleContribution contribution = new IExampleContribution() {

			@Override
			public String getId() {
				return id;
			}

			@Override
			public String getTitle() {
				return title;
			}

			@Override
			public IExamplePage createPage() {
				try {
					return clazz.newInstance();
				} catch (final Exception exception) {
					throw new RuntimeException("Failed to instatiate class " + clazz.getName(), exception); //$NON-NLS-1$
				}
			}
		};
		contributions.add(contribution);
	}

}
