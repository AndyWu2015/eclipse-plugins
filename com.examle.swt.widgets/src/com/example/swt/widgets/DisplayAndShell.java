package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DisplayAndShell {

	public static void main(String[] args) {
		Display display = new Display();
		Color color =  new Color(display,255,0,0);

		//create a shell
		final Shell shell_1 = new Shell(display);
		shell_1.setText("This is a shell in main function()");
		shell_1.setBounds(100,100,400,200);
		shell_1.setLayout(new FillLayout());

		Label label_1 = new Label(shell_1,SWT.CENTER);
		label_1.setText("this is the text of a label");
		label_1.setForeground(color);
		
		shell_1.open();

		Text test;
		//create another shell
		Shell shell_2 = new Shell(display);
		shell_2.setText("This is a shell1 in main function()");
		shell_2.setBounds(250,250,400,200);
		shell_2.setLayout(new FillLayout());

		Label label_2 = new Label(shell_2,SWT.CENTER);
		label_2.setText("this is the text of a label1");
		label_2.setForeground(color);

		shell_2.open();

		while(!shell_1.isDisposed() || !shell_2.isDisposed()){
		    if(!display.readAndDispatch())
		        display.sleep();
		}

		//dispose the resource
		display.beep();
		color.dispose();
		display.dispose();

	}

}
