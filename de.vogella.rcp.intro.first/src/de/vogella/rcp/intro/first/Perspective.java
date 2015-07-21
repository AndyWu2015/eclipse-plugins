package de.vogella.rcp.intro.first;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		//ʹ�ô��������ͼ
		layout.addView("de.vogella.rcp.intro.first.MyView", IPageLayout.TOP,
		        IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);
		//��������
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		
	}

}
