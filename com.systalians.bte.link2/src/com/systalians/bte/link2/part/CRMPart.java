package com.systalians.bte.link2.part;

import javax.inject.Inject;

import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.systalians.bte.services.serviceconf.Configuration;
import com.systalians.bte.services.serviceconf.ConfigurationManager;
import com.systalians.bte.services.serviceconf.ConversionException;
import com.systalians.bte.services.serviceconf.ServiceConfiguration;

/**
 * Part qui affiche un navigateur web contenant CRM.
 * 
 * @author lemoinef
 * 
 */

public class CRMPart {

	private static final String SELLIGENT_URL_KEY_CONF = "selligent.url";
	private static final Logger LOGGER = LoggerFactory.getLogger(CRMPart.class);

	/**
	 * Initialisation de la Part
	 * 
	 * @param parent
	 * @param workbench
	 */
	@Inject
	public void init(Composite parent,
			@SuppressWarnings("restriction") IWorkbench workbench) {

		parent.getShell().setImages(getShellImages());

		this.createView(parent, workbench);
	}

	/**
	 * Création de la vue contenu dans la part
	 * 
	 * @param parent
	 * @param workbench
	 */
	private void createView(Composite parent,
			@SuppressWarnings("restriction") IWorkbench workbench) {

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		parent.setLayout(gridLayout);

		GridData data = new GridData();

		final Browser browser;
		browser = new Browser(parent, SWT.NONE);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.horizontalSpan = 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);

//		final Label status = new Label(parent, SWT.NONE);
//		data = new GridData(GridData.FILL_HORIZONTAL);
//		data.horizontalSpan = 2;
//		status.setLayoutData(data);
//
//		final ProgressBar progressBar = new ProgressBar(parent, SWT.NONE);
//		data = new GridData();
//		data.horizontalAlignment = GridData.END;
//		progressBar.setLayoutData(data);
//
//		browser.addProgressListener(new ProgressListener() {
//			public void changed(ProgressEvent event) {
//				if (event.total == 0)
//					return;
//				int ratio = event.current * 100 / event.total;
//				progressBar.setSelection(ratio);
//			}
//
//			public void completed(ProgressEvent event) {
//				progressBar.setSelection(0);
//			}
//		});
//		browser.addStatusTextListener(new StatusTextListener() {
//			public void changed(StatusTextEvent event) {
//				status.setText(event.text);
//			}
//		});

		browser.setUrl(this.getUrlSelligent());

	}

	/**
	 * @return une string donnant l'url de Selligent
	 */
	private String getUrlSelligent() {
		ServiceConfiguration serviceConfiguration = ConfigurationManager
				.getInstance().getServiceConfiguration();
		Configuration configuration = serviceConfiguration.getConfiguration();

		String urlConf = null;
		try {
			urlConf = configuration.getString(SELLIGENT_URL_KEY_CONF);
		} catch (ConversionException e) {
			// LOGGER.error("URL de Selligent non disponible :", e);
		}
		return urlConf;
	}

	/**
	 * Retourne l'icone de l'ESIC
	 * 
	 * @return
	 */
	protected Image[] getShellImages() {
		Image[] windowImages = { SWTResourceManager.getInstance().getImage(
				"/icons/Link2.png") };
		return windowImages;
	}

}