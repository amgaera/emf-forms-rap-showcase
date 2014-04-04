package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.model.provider.xmi.ViewModelFileExtensionsManager;
import org.eclipse.emf.ecp.view.spi.model.VView;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.rap.rwt.service.UISession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.eclipsesource.makeithappen.model.task.TaskFactory;
import com.eclipsesource.makeithappen.model.task.User;

@SuppressWarnings("restriction")
public class BasicEntryPoint extends AbstractEntryPoint {

	public User user;

	@Override
	protected void createContents(Composite parent) {
		user = TaskFactory.eINSTANCE.createUser();

		final Button button = new Button(parent, SWT.PUSH);
		button.setText("World"); //$NON-NLS-1$

		button.addSelectionListener(new SelectionListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("widgetSelected"); //$NON-NLS-1$
				System.out.println(user);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

		});

		setupRealm(Display.getCurrent());

		final URI uri = URI.createURI("platform:/plugin/rap/viewmodel/user.viewmodel"); //$NON-NLS-1$
		final Resource resource = ViewModelFileExtensionsManager.loadResource(uri);
		final EObject eObject = resource.getContents().get(0);
		final VView view = (VView) eObject;

		try {
			ECPSWTViewRenderer.INSTANCE.render(parent, user, view);
		} catch (final ECPRendererException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setupRealm(Display display) {
		final UISession uiSession = RWT.getUISession();
		if (uiSession.getAttribute("realm") == null) { //$NON-NLS-1$
			final Realm realm = SWTObservables.getRealm(display);
			RealmSetter.setRealm(realm);
			RWT.getUISession().setAttribute("realm", realm); //$NON-NLS-1$
		}
	}

}
