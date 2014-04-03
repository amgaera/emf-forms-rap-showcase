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
import org.eclipse.emf.ecp.view.demo.rap.common.TablesExamplePage;

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
		addContribution("button", "Buttons", ButtonExamplePage.class);
		addContribution("table-template", "Tables", TablesExamplePage.class);
	}

	private void addContribution(final String id,
		final String title,
		final Class<? extends IExamplePage> clazz)
	{
		final IExampleContribution contribution = new IExampleContribution() {

			public String getId() {
				return id;
			}

			public String getTitle() {
				return title;
			}

			public IExamplePage createPage() {
				try {
					return clazz.newInstance();
				} catch (final Exception exception) {
					throw new RuntimeException("Failed to instatiate class " + clazz.getName(), exception);
				}
			}
		};
		contributions.add(contribution);
	}

}
