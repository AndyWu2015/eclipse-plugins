package de.vogella.rcp.dialogs.swt;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		//layout.addView(View.ID, IPageLayout.LEFT, IPageLayout.RATIO_MAX, layout.getEditorArea());
		
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
	}
}
