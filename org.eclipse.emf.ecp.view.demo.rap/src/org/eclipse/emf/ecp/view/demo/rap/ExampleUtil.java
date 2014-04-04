package org.eclipse.emf.ecp.view.demo.rap;

import java.io.File;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public final class ExampleUtil {

	private static final int DEFAULT_SPACE = 10;

	private static final String DATA_DIR_PROP = "org.eclipse.rap.examples.dataDir"; //$NON-NLS-1$
	private static final String DEFAULT_DATA_DIR = "/data/rapdemo"; //$NON-NLS-1$

	public static File getDataDirectory() {
		return new File(System.getProperty(DATA_DIR_PROP, DEFAULT_DATA_DIR));
	}

	public static Composite initPage(String title, Composite parent) {
		final Composite pageComp = new Composite(parent, SWT.NONE);
		pageComp.setLayout(ExampleUtil.createGridLayoutWithoutMargin(1, false));
		final Label label = createHeadlineLabel(pageComp, title);
		label.setLayoutData(createHeadlineLayoutData());
		final Composite contentComp = new Composite(pageComp, SWT.NONE);
		contentComp.setLayoutData(ExampleUtil.createFillData());
		return contentComp;
	}

	public static void createHeading(Composite parent, String text,
		int horizontalSpan) {
		final Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		label.setData(RWT.CUSTOM_VARIANT, "heading"); //$NON-NLS-1$
		final GridData labelLayoutData = new GridData();
		labelLayoutData.horizontalSpan = horizontalSpan;
		label.setLayoutData(labelLayoutData);
	}

	public static GridLayout createMainLayout(int numColumns) {
		final GridLayout result = new GridLayout(numColumns, true);
		result.marginWidth = 0;
		result.marginHeight = 0;
		result.marginTop = 0;
		result.verticalSpacing = 0;
		result.horizontalSpacing = 60;
		return result;
	}

	public static GridLayout createMainLayout(int numColumns, int horzSpacing) {
		final GridLayout result = new GridLayout(numColumns, true);
		result.marginWidth = 0;
		result.marginHeight = 0;
		result.marginTop = 0;
		result.verticalSpacing = 0;
		result.horizontalSpacing = horzSpacing;
		return result;
	}

	public static GridLayout createGridLayout(int numColumns,
		boolean makeColsEqualWidth, boolean setTopMargin,
		boolean setVertSpacing) {
		final GridLayout result = new GridLayout(numColumns, makeColsEqualWidth);
		result.marginWidth = DEFAULT_SPACE;
		result.marginHeight = 0;
		result.marginBottom = DEFAULT_SPACE;
		result.horizontalSpacing = DEFAULT_SPACE;
		if (setTopMargin) {
			result.marginTop = DEFAULT_SPACE;
		}
		if (setVertSpacing) {
			result.verticalSpacing = DEFAULT_SPACE;
		}
		return result;
	}

	public static GridLayout createGridLayoutWithoutMargin(int numColumns,
		boolean makeColsEqualWidth) {
		final GridLayout result = new GridLayout(numColumns, makeColsEqualWidth);
		result.marginHeight = 0;
		result.marginWidth = 0;
		return result;
	}

	public static RowLayout createRowLayout(int type, boolean setMargin) {
		final RowLayout result = new RowLayout(type);
		result.marginTop = 0;
		result.marginLeft = 0;
		result.marginHeight = 0;
		if (setMargin) {
			result.marginBottom = DEFAULT_SPACE;
			result.marginWidth = DEFAULT_SPACE;
		} else {
			result.marginBottom = 0;
			result.marginWidth = 0;
		}
		return result;
	}

	public static FillLayout createFillLayout(boolean setMargin) {
		final FillLayout result = new FillLayout();
		if (setMargin) {
			result.marginWidth = DEFAULT_SPACE;
			result.marginHeight = DEFAULT_SPACE;
		}
		return result;
	}

	public static GridData createHorzFillData() {
		return new GridData(SWT.FILL, SWT.TOP, true, false);
	}

	public static GridData createFillData() {
		return new GridData(SWT.FILL, SWT.FILL, true, true);
	}

	private static Label createHeadlineLabel(Composite parent, String text) {
		final Label label = new Label(parent, SWT.NONE);
		label.setText(text.replace("&", "&&")); //$NON-NLS-1$ //$NON-NLS-2$
		label.setData(RWT.CUSTOM_VARIANT, "pageHeadline"); //$NON-NLS-1$
		return label;
	}

	private static GridData createHeadlineLayoutData() {
		final GridData layoutData = new GridData();
		layoutData.verticalIndent = 30;
		layoutData.horizontalIndent = DEFAULT_SPACE;
		return layoutData;
	}

	private ExampleUtil() {
		// prevent instantiation
	}

}
