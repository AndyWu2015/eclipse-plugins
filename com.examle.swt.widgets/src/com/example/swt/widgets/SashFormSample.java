package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SashFormSample {

	public static void main(String[] args) {

		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		Composite composite = new Composite(shell, SWT.NONE);

		composite.setLayout(new GridLayout(1, true));

		final SashForm sashForm = new SashForm(composite, SWT.VERTICAL | SWT.BORDER);

		final Button b1 = new Button(sashForm, SWT.PUSH);
		b1.setText("Button1");

		final Button b2 = new Button(sashForm, SWT.PUSH);
		b2.setText("Button2");

		final Button b3 = new Button(sashForm, SWT.PUSH);
		b3.setText("Button3");

		Composite buttonContainer = new Composite(composite, SWT.BORDER);

		buttonContainer.setLayout(new GridLayout(2, true));

		Button b4 = new Button(buttonContainer, SWT.PUSH);

		b4.setText("maximize b2");
		b4.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				sashForm.setMaximizedControl(b2);
			}
		});

		Button b5 = new Button(buttonContainer, SWT.PUSH);
		b5.setText("reset");
		b5.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				sashForm.setMaximizedControl(null);
			}
		});

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
