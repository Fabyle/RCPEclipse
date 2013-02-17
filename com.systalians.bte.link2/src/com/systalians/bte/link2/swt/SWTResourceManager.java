package com.systalians.bte.link2.swt;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Outils permettant de manager les ressources SWT (images, couleur, prévoir fonts )
 * @author lemoinef
 *
 */
public class SWTResourceManager {

	private ImageRegistry imageRegistry;
	private ColorRegistry colorRegistry;
	//private FontRegistry fontRegistry;
	
		
	/**
	 * Classe permettant d'obtenir le singleton
	 * @author lemoinef
	 *
	 */
	private static class Holder {

		static final SWTResourceManager INSTANCE = new SWTResourceManager();

		private Holder() {
		}
	}
	
	public static SWTResourceManager getInstance() {
		return Holder.INSTANCE;
	}
	

	
	/**
	 * Constructeur
	 */
	public SWTResourceManager() {
		imageRegistry = new ImageRegistry(Display.getCurrent());
		colorRegistry = new ColorRegistry(Display.getCurrent());
		
	}
	
	/**
	 * retourne l'image vérifiant le string <path>
	 * Retourne une image d'après un chemin relatif à la racine du bundle contenant
	 * la classe SWTResourceManager
	 * @param path
	 * @return
	 */
	public Image getImage(String path) {
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		return getImage(path, bundle);
	}

	/**
	 * Retourne le l'image vérifiant le <path> et le <bundle>
	 * @param path
	 * @param bundle
	 * @return
	 */
	public Image getImage(String path, Bundle bundle) {	
		URL url = FileLocator.find(bundle, new Path(path), null);
		if(imageRegistry.get(path) == null) {
			ImageDescriptor desc = ImageDescriptor.createFromURL(url);
			imageRegistry.put(path, desc);
		}
		return imageRegistry.get(path);
	}
	
	/**
	 * retourne la couleur vérifiant <rgbString>
	 * @param rgbString
	 * @return
	 */
	public Color getColor(String rgbString) {
		if (!colorRegistry.hasValueFor(rgbString)) {
			int red = Integer.parseInt(rgbString.substring(1, 3), 16);
			int green = Integer.parseInt(rgbString.substring(3, 5), 16);
			int blue = Integer.parseInt(rgbString.substring(5, 7), 16);
			colorRegistry.put(rgbString, new RGB(red, green, blue));
		}
		return colorRegistry.get(rgbString);
	}
}
