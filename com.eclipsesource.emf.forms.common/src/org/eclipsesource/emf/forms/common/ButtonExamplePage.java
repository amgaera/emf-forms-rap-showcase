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
import org.eclipse.emf.ecp.view.model.provider.xmi.ViewModelFileExtensionsManager;
import org.eclipse.emf.ecp.view.spi.model.VView;
import org.eclipse.emf.forms.main.IExamplePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.eclipsesource.makeithappen.model.task.TaskFactory;
import com.eclipsesource.makeithappen.model.task.User;


public class ButtonExamplePage implements IExamplePage {

	protected Image errorImage;
	protected Image warningImage;

	public void createControl( Composite parent ) {
		User user = TaskFactory.eINSTANCE.createUser();
		ViewModelFileExtensionsManager viewModelFileExtensionsManager = ViewModelFileExtensionsManager.getInstance();
		URI uri = URI.createURI("platform:/plugin/rap/viewmodel/user.viewmodel");
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
