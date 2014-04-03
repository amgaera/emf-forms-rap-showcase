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
package org.eclipse.emf.ecp.view.demo.rap.internal;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.ecp.view.demo.rap.ExampleUtil;
import org.eclipse.emf.ecp.view.demo.rap.IExampleContribution;
import org.eclipse.emf.ecp.view.demo.rap.IExamplePage;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.rap.rwt.client.service.BrowserNavigation;
import org.eclipse.rap.rwt.client.service.BrowserNavigationEvent;
import org.eclipse.rap.rwt.client.service.BrowserNavigationListener;
import org.eclipse.rap.rwt.client.service.JavaScriptExecutor;
import org.eclipse.rap.rwt.service.UISession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

//
public class MainUi extends AbstractEntryPoint {

	private static final String RAP_PAGE_URL = "http://eclipse.org/rap/";
	private static final int CONTENT_MIN_HEIGHT = 800;
	private static final int HEADER_HEIGHT = 140;
	private static final int CENTER_AREA_WIDTH = 998;

	private Composite centerArea;
	private Navigation navigation;
	private Composite navBar;

	@Override
	protected Shell createShell(Display display) {
		final Shell shell = super.createShell(display);
		shell.setData(RWT.CUSTOM_VARIANT, "mainshell");
		return shell;
	}

	@Override
	protected void createContents(Composite parent) {
		setupRealm(Display.getCurrent());

		parent.setLayout(new FillLayout());
		final ScrolledComposite scrolledArea = createScrolledArea(parent);
		final Composite content = createContent(scrolledArea);
		scrolledArea.setContent(content);
		attachHistoryListener();
		selectInitialContribution();
	}

	protected void setupRealm(Display display) {
		final UISession uiSession = RWT.getUISession();
		if (uiSession.getAttribute("realm") == null) {
			final Realm realm = SWTObservables.getRealm(display);
			RealmSetter.setRealm(realm);
			RWT.getUISession().setAttribute("realm", realm);
		}
	}

	private void attachHistoryListener() {
		final BrowserNavigation history = RWT.getClient().getService(
			BrowserNavigation.class);
		if (history != null) {
			history.addBrowserNavigationListener(new BrowserNavigationListener() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void navigated(BrowserNavigationEvent event) {
					final Examples examples = Examples.getInstance();
					final IExampleContribution contribution = examples
						.getContribution(event.getState());
					if (contribution != null) {
						selectContribution(contribution);
					}
				}
			});
		}
	}

	private void selectInitialContribution() {
		final IExampleContribution contribution = Examples.getInstance()
			.findInitialContribution();
		if (contribution != null) {
			selectContribution(contribution);
		}
	}

	private ScrolledComposite createScrolledArea(Composite parent) {
		final ScrolledComposite scrolledComp = new ScrolledComposite(parent,
			SWT.V_SCROLL | SWT.H_SCROLL);
		scrolledComp.setMinHeight(CONTENT_MIN_HEIGHT);
		scrolledComp.setMinWidth(CENTER_AREA_WIDTH);
		scrolledComp.setExpandVertical(true);
		scrolledComp.setExpandHorizontal(true);
		return scrolledComp;
	}

	private Composite createContent(ScrolledComposite scrolledArea) {
		final Composite comp = new Composite(scrolledArea, SWT.NONE);
		comp.setLayout(new FormLayout());
		final Composite header = createHeader(comp);
		header.setLayoutData(createHeaderFormData());
		createContentBody(comp, header);
		return comp;
	}

	private Composite createHeader(Composite parent) {
		final Composite comp = new Composite(parent, SWT.NONE);
		comp.setData(RWT.CUSTOM_VARIANT, "header");
		comp.setBackgroundMode(SWT.INHERIT_DEFAULT);
		comp.setLayout(new FormLayout());
		final Composite headerCenterArea = createHeaderCenterArea(comp);
		createLogo(headerCenterArea);
		createTitle(headerCenterArea);
		return comp;
	}

	private FormData createHeaderFormData() {
		final FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(50, -CENTER_AREA_WIDTH / 2);
		data.width = CENTER_AREA_WIDTH;
		data.height = HEADER_HEIGHT;
		return data;
	}

	private Composite createHeaderCenterArea(Composite parent) {
		final Composite headerCenterArea = new Composite(parent, SWT.NONE);
		headerCenterArea.setLayout(new FormLayout());
		headerCenterArea.setLayoutData(createHeaderCenterAreaFormData());
		return headerCenterArea;
	}

	private FormData createHeaderCenterAreaFormData() {
		final FormData data = new FormData();
		data.left = new FormAttachment(50, -CENTER_AREA_WIDTH / 2);
		data.top = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.width = CENTER_AREA_WIDTH;
		return data;
	}

	private void createLogo(Composite headerComp) {
		final Label logoLabel = new Label(headerComp, SWT.NONE);
		final Image rapLogo = MainUi
			.getImage(headerComp.getDisplay(), "logo.png");
		logoLabel.setImage(rapLogo);
		logoLabel.setLayoutData(createLogoFormData(rapLogo));
		makeLink(logoLabel, RAP_PAGE_URL);
	}

	private void createTitle(Composite headerComp) {
		final Label title = new Label(headerComp, SWT.NONE);
		title.setText("EMF Forms Showcase");
		title.setLayoutData(createTitleFormData());
		title.setData(RWT.CUSTOM_VARIANT, "title");
	}

	private void createContentBody(Composite parent, Composite header) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setData(RWT.CUSTOM_VARIANT, "mainContentArea");
		composite.setLayout(new FormLayout());
		composite.setLayoutData(createContentBodyFormData(header));
		setNavigation(createNavigation(composite));
		final Composite footer = createFooter(composite);
		setCenterArea(createCenterArea(composite, footer));
	}

	private Composite createCenterArea(Composite parent, Composite footer) {
		final Composite centerArea = new Composite(parent, SWT.NONE);
		centerArea.setLayout(new FillLayout());
		centerArea.setLayoutData(createCenterAreaFormData(footer));
		centerArea.setData(RWT.CUSTOM_VARIANT, "centerArea");
		return centerArea;
	}

	private FormData createCenterAreaFormData(Composite footer) {
		final FormData data = new FormData();
		data.top = new FormAttachment(navBar, 0, SWT.BOTTOM);
		data.bottom = new FormAttachment(footer, 0, SWT.TOP);
		data.left = new FormAttachment(50, -CENTER_AREA_WIDTH / 2);
		data.width = CENTER_AREA_WIDTH + 10;
		return data;
	}

	private Composite createFooter(Composite contentComposite) {
		final Composite footer = new Composite(contentComposite, SWT.NONE);
		footer.setBackgroundMode(SWT.INHERIT_DEFAULT);
		footer.setLayout(new FormLayout());
		footer.setData(RWT.CUSTOM_VARIANT, "footer");
		footer.setLayoutData(createFooterFormData());
		final Label label = new Label(footer, SWT.NONE);
		label.setData(RWT.CUSTOM_VARIANT, "footerLabel");
		label.setText("RAP version: " + getRapVersion());
		label.setLayoutData(createFooterLabelFormData(footer));
		return footer;
	}

	private FormData createFooterFormData() {
		final FormData data = new FormData();
		data.left = new FormAttachment(50, -CENTER_AREA_WIDTH / 2);
		data.top = new FormAttachment(100, -40);
		data.bottom = new FormAttachment(100);
		data.width = CENTER_AREA_WIDTH - 10 - 2;
		return data;
	}

	private FormData createFooterLabelFormData(Composite footer) {
		final FormData data = new FormData();
		data.top = new FormAttachment(50, -10);
		data.right = new FormAttachment(100, -15);
		return data;
	}

	private FormData createContentBodyFormData(Composite header) {
		final FormData data = new FormData();
		data.top = new FormAttachment(header, 0);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		return data;
	}

	private Navigation createNavigation(Composite parent) {
		navBar = new Composite(parent, SWT.NONE);
		navBar.setLayout(new FormLayout());
		navBar.setLayoutData(createNavBarFormData());
		navBar.setData(RWT.CUSTOM_VARIANT, "nav-bar");
		final Navigation navigation = new Navigation(navBar) {
			@Override
			protected void selectContribution(IExampleContribution contribution) {
				MainUi.this.selectContribution(contribution);
			}
		};
		final Control navigationControl = navigation.getControl();
		navigationControl.setLayoutData(createNavigationFormData());
		navigationControl.setData(RWT.CUSTOM_VARIANT, "navigation");
		return navigation;
	}

	private void selectContribution(IExampleContribution contribution) {
		activate(contribution);
	}

	private void activate(IExampleContribution contribution) {
		final IExamplePage examplePage = contribution.createPage();
		if (examplePage != null) {
			final BrowserNavigation history = RWT.getClient().getService(BrowserNavigation.class);
			if (history != null) {
				history.pushState(contribution.getTitle(), contribution.getTitle());
			}
			final Control[] children = centerArea.getChildren();
			for (final Control child : children) {
				child.dispose();
			}
			final Composite contentComp = ExampleUtil.initPage(contribution.getTitle(), centerArea);
			examplePage.createControl(contentComp);
			centerArea.layout(true, true);
		}
	}

	private static FormData createLogoFormData(Image rapLogo) {
		final FormData data = new FormData();
		data.left = new FormAttachment(0);
		final int logoHeight = rapLogo.getBounds().height;
		data.top = new FormAttachment(50, -(logoHeight / 2));
		return data;
	}

	private static FormData createTitleFormData() {
		final FormData data = new FormData();
		data.bottom = new FormAttachment(100, -26);
		data.left = new FormAttachment(0, 250);
		return data;
	}

	private static FormData createNavBarFormData() {
		final FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(50, -CENTER_AREA_WIDTH / 2);
		return data;
	}

	private static FormData createNavigationFormData() {
		final FormData data = new FormData();
		data.left = new FormAttachment(50, -CENTER_AREA_WIDTH / 2);
		data.top = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.width = CENTER_AREA_WIDTH;
		return data;
	}

	public static Image getImage(Display display, String path) {
		final ClassLoader classLoader = MainUi.class.getClassLoader();
		final InputStream inputStream = classLoader.getResourceAsStream("resources/"
			+ path);
		Image result = null;
		if (inputStream != null) {
			try {
				result = new Image(display, inputStream);
			} finally {
				try {
					inputStream.close();
				} catch (final IOException e) {
					// ignore
				}
			}
		}
		return result;
	}

	private static String getRapVersion() {
		final Version version = FrameworkUtil.getBundle(RWT.class).getVersion();
		final StringBuilder resultBuffer = new StringBuilder(20);
		resultBuffer.append(version.getMajor());
		resultBuffer.append('.');
		resultBuffer.append(version.getMinor());
		resultBuffer.append('.');
		resultBuffer.append(version.getMicro());
		resultBuffer.append(" (Build ");
		resultBuffer.append(version.getQualifier());
		resultBuffer.append(')');
		return resultBuffer.toString();
	}

	private static void makeLink(Label control, final String url) {
		control.setCursor(control.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
		control.addMouseListener(new MouseAdapter() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseDown(MouseEvent e) {
				final JavaScriptExecutor executor = RWT.getClient().getService(
					JavaScriptExecutor.class);
				if (executor != null) {
					executor.execute("window.location.href = '" + url + "'");
				}
			}
		});
	}

	public Composite getCenterArea() {
		return centerArea;
	}

	public void setCenterArea(Composite centerArea) {
		this.centerArea = centerArea;
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

}
