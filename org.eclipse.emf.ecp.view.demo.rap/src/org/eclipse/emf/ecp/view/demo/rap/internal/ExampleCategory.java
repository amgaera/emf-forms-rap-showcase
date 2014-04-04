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

public class ExampleCategory {

	private final String name;
	public String contributionId;

	public ExampleCategory(String name, String contributionId) {
		this.name = name;
		this.contributionId = contributionId;
	}

	public String getName() {
		return name;
	}

	public String getContributionId()
	{
		return contributionId;
	}

}
