package de.vogella.rcp.intro.first;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		//使用代码添加视图
		layout.addView("de.vogella.rcp.intro.first.MyView", IPageLayout.TOP,
		        IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);
		//正常代码
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		
	}

}
