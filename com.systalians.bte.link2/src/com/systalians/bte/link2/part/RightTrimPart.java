package com.systalians.bte.link2.part;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class RightTrimPart {
	
	

	@Inject
	MWindow window;

	private boolean isOpen = false;
	private static FontRegistry FontRegistry;
	private static String Selected = "selected";
	private static String UnSelected = "UnSelected";
	
	
	@Inject
	public void init(Composite parent,
			@SuppressWarnings("restriction") IWorkbench workbench) {
		
		initFontRegistry(parent.getDisplay());
		
		// init du layout
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.marginRight = 10;
		rowLayout.marginTop = 15;
		rowLayout.spacing = 10;
		parent.setLayout(rowLayout);
		
		labelOuvrirLink(parent);
		
		labelVide(parent);
		
		// séparateur
		new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);	
		
		labelClient(parent);		
		
		labelClientDansContexte(parent);
		
		// séparateur
		new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);		
			
				
	}

	/**
	 * @param parent
	 */
	private void labelClientDansContexte(Composite parent) {
		// titre du client
		final Display display = parent.getDisplay();
		final Label labelClientDansContexte = new Label(parent, SWT.NONE);
		labelClientDansContexte.setText("Mr Durand");
		labelClientDansContexte.setFont(FontRegistry.get(Selected));
		
		labelClientDansContexte.addMouseTrackListener(new MouseTrackListener() {

			BulleClient bulle = new BulleClient();;
			
			@Override
			public void mouseEnter(MouseEvent arg0) {
				 bulle.ouvrir(display, labelClientDansContexte.getBounds(), window);
				
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				bulle.fermer();
				
			}

			@Override
			public void mouseHover(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	/**
	 * @param parent
	 */
	private void labelClient(Composite parent) {
		// label client
		Label labelClient = new Label(parent, SWT.NONE);
		labelClient.setImage(SWTResourceManager.getInstance().getImage(
						"/icons/ClientTexte2.png"));
	}

	/**
	 * @param parent
	 */
	private void labelVide(Composite parent) {
		// label vide pour espace
		final Label labelVide = new Label(parent, SWT.NONE);
		labelVide.setImage(SWTResourceManager.getInstance().getImage(
				"/icons/LinkIcoInvisible.png"));
	}

	/**
	 * @param parent
	 */
	private void labelOuvrirLink(Composite parent) {
		// label Link
		final Label label = new Label(parent, SWT.NONE);
		label.setImage(SWTResourceManager.getInstance().getImage(
				"/icons/Link2NB.png"));
		gererOuvertureLink(label);
	}

	/**
	 * Assure l'ouverture et la fermeture de Link
	 * @param label
	 */
	private void gererOuvertureLink(final Label label) {
		label.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExit(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEnter(MouseEvent e) {

				MPerspectiveStack mPerspectiveStack = (MPerspectiveStack) window
						.getChildren().get(0);
				MPerspective mPerspective = mPerspectiveStack.getChildren()
						.get(0);
				MPartSashContainer mPartSash = (MPartSashContainer) mPerspective
						.getChildren().get(0);

				// Premier sash
				MPart mPartCRM = (MPart) mPartSash.getChildren().get(0);
				MPartStack mPartLink = (MPartStack) mPartSash
						.getChildren().get(1);

				if (!isOpen) {
					mPartCRM.setContainerData("80");
					mPartLink.setContainerData("20");
					isOpen = true;
					label.setImage(SWTResourceManager.getInstance().getImage(
							"/icons/Link2.png"));
					//label.setToolTipText(CLOSE_LINK);
				} else {
					mPartCRM.setContainerData("100");
					mPartLink.setContainerData("0");
					isOpen = false;
					label.setImage(SWTResourceManager.getInstance().getImage(
							"/icons/Link2NB.png"));
					//label.setToolTipText(OPEN_LINK);

				}

			}

		});
	}
	
	/**
	 * Initialisation des fonts pour les onglets.
	 * @param display
	 * @return
	 */
	private FontRegistry initFontRegistry(Display display) {

		if (FontRegistry == null) {
			FontRegistry = new FontRegistry(display);
			// couleur unselected : #416AA3
			FontRegistry.put(UnSelected, new FontData[] { new FontData("Tahoma", 8, SWT.NORMAL) });
			// couleur selected : #15428B
			FontRegistry.put(Selected, new FontData[] { new FontData("Tahoma", 8, SWT.BOLD) });
		}
		return FontRegistry;

	}
}
