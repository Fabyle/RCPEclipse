/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.systalians.bte.link2.part;

import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Region snippet: Create non-rectangular shell from an image with transparency
 * 
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.2
 */
public class BulleClient {

	Shell shell;

	final Image image = SWTResourceManager.getInstance().getImage(
			"/icons/bulle.png");

	final Image imageBack = SWTResourceManager.getInstance().getImage(
			"/icons/backgroundBulle.png");

	public void ouvrir(final Display display, Rectangle labelBounds,
			MWindow window) {

		if (shell == null) {

			constructShell(display, labelBounds, window);
		} else {
			shell.open();
		}

	}

	/**
	 * @param display
	 * @param labelBounds
	 * @param window
	 */
	private void constructShell(final Display display, Rectangle labelBounds,
			MWindow window) {
		shell = new Shell(display, SWT.NO_TRIM);

		final ImageData imageData = designShellForm();

		addPaintingListener(imageData);

		addLabelForContext(imageData);

		shell.setSize(imageData.x + imageData.width, imageData.y
				+ imageData.height);
		shell.setLocation(window.getX() + window.getWidth() - imageData.width
				- 45, window.getY() + labelBounds.y - imageData.height + 45);
		shell.open();
	}

	/**
	 * @param imageData
	 */
	private void addLabelForContext(final ImageData imageData) {
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(30, 30, imageData.width - 80, imageData.height - 80);
		label.setBackgroundImage(imageBack);
		label.setText("Personne physique : \n \tMr Jacques Durand");
	}

	/**
	 * @param imageData
	 */
	private void addPaintingListener(final ImageData imageData) {
		Listener l = new Listener() {

			public void handleEvent(Event e) {
				if (e.type == SWT.Paint) {
					e.gc.drawImage(image, imageData.x, imageData.y);
				}
			}
		};

		shell.addListener(SWT.Paint, l);
	}

	/**
	 * @return
	 */
	private ImageData designShellForm() {
		Region region = new Region();
		final ImageData imageData = image.getImageData();
		if (imageData.alphaData != null) {
			Rectangle pixel = new Rectangle(0, 0, 1, 1);
			for (int y = 0; y < imageData.height; y++) {
				for (int x = 0; x < imageData.width; x++) {
					if (imageData.getAlpha(x, y) == 255) {
						pixel.x = imageData.x + x;
						pixel.y = imageData.y + y;
						region.add(pixel);
					}
				}
			}
		} else {
			ImageData mask = imageData.getTransparencyMask();
			Rectangle pixel = new Rectangle(0, 0, 1, 1);
			for (int y = 0; y < mask.height; y++) {
				for (int x = 0; x < mask.width; x++) {
					if (mask.getPixel(x, y) != 0) {
						pixel.x = imageData.x + x;
						pixel.y = imageData.y + y;
						region.add(pixel);
					}
				}
			}
		}
		shell.setRegion(region);
		return imageData;
	}

	/**
	 * permet de fermer la bulle
	 */
	public void fermer() {
		shell.setVisible(false);

	}
}