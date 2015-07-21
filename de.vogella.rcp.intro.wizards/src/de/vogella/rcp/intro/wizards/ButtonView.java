package de.vogella.rcp.intro.wizards;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.vogella.rcp.intro.wizards.wizard.MyWizard;

public class ButtonView extends Composite{
	
	public ButtonView(final Composite parent, int style) {
	    super(parent, style);    
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Open Wizard");
		button.addSelectionListener(new SelectionAdapter() {
		  @Override
		  public void widgetSelected(SelectionEvent e) {
		    WizardDialog wizardDialog = new WizardDialog(parent.getShell(), new MyWizard());
		    if (wizardDialog.open() == Window.OK) {
		      System.out.println("Ok pressed");
		    } else {
		      System.out.println("Cancel pressed");
		      }
		  }
		}); 
	  }
}
