package com.systalians.bte.link2.part;

import javax.inject.Inject;

import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

public class ContextePart {
	
	// IStylingEngine is injected
	@Inject IStylingEngine engine;

	/**
	 * Initialisation de la Part
	 * 
	 * @param parent
	 * @param workbench
	 */
	@Inject
	public void init(Composite parent,
			@SuppressWarnings("restriction") IWorkbench workbench) {

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

		parent.setLayout(new GridLayout());
		Table table = new Table (parent, SWT.READ_ONLY);
		table.setHeaderVisible (true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		table.setLayoutData(data);
		String[] titles = {"Type de client", "Client"};
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [i]);
		}	
		TableItem item = new TableItem (table, SWT.NONE);
		item.setText (0, "Personne Physique");
		item.setText (1, "Mr Guillaume Mars");
			
		
		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}	
		
		
		
		
		
		
	}

}
