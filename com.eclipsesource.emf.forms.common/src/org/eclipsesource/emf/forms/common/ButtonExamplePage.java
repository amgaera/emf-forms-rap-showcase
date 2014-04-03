/*******************************************************************************
 * Copyright (c) 2009, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipsesource.emf.forms.common;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
// TODO: add a note to the contribution that we'd like the bundle org.eclipse.emf.ecp.view.model.provider.xmi to declare us as an x-friend
import org.eclipse.emf.ecp.view.model.provider.xmi.ViewModelFileExtensionsManager;
import org.eclipse.emf.ecp.view.spi.model.VView;
import org.eclipse.emf.forms.main.IExamplePage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.eclipsesource.makeithappen.model.task.TaskFactory;
import com.eclipsesource.makeithappen.model.task.User;


public class ButtonExamplePage implements IExamplePage {

	public void createControl( Composite parent ) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		parent.setLayout(gridLayout);

		User user = TaskFactory.eINSTANCE.createUser();
		ViewModelFileExtensionsManager viewModelFileExtensionsManager = ViewModelFileExtensionsManager.getInstance();
		URI uri = URI.createURI("platform:/plugin/org.eclipsesource.emf.forms.common/viewmodel/user.viewmodel");
		final Resource resource = viewModelFileExtensionsManager.loadResource(uri);
		final EObject eObject = resource.getContents().get(0);
		final VView view = (VView) eObject;

		try {
			ECPSWTViewRenderer.INSTANCE.render(parent, user, view);
		} catch (ECPRendererException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
