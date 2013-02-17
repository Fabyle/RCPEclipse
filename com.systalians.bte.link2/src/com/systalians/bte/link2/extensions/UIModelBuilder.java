package com.systalians.bte.link2.extensions;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.swt.widgets.Display;

public class UIModelBuilder {
	
	private MWindow window;

	/**
	 * Méthode solliciter au démarrage de l'application. 
	 * Gere l'ouverture du splash screen
	 * Construit l'application en fonction de la description
	 * @param application
	 */
	@ProcessAdditions
	void build(MApplication application) {
		
		Display display = Display.getCurrent();
		
		window = application.getChildren().get(0);
		window.setWidth(1280);
		window.setHeight(1024);
		window.setX((display.getBounds().width - window.getWidth()) / 2);
		window.setY((display.getBounds().height - window.getHeight()) / 2);
				
		MPerspectiveStack mPerspectiveStack = (MPerspectiveStack) window.getChildren().get(0);
		MPerspective mPerspective = mPerspectiveStack.getChildren().get(0);
		MPartSashContainer mPartSash = (MPartSashContainer) mPerspective.getChildren().get(0);
		
		// Premier sash
		MPart mPartCRM = (MPart) mPartSash.getChildren().get(0);
		MPartStack mPartLink = (MPartStack) mPartSash.getChildren().get(1);
		mPartCRM.setContainerData("100");		
		mPartLink.setContainerData("00");
		
	

	
	}

	
	

}
