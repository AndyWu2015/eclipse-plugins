
package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ScrollCompisiteHoriVertical
{
    public static void main( String[] args )
    {
        Display display = new Display();
        Shell shell = new Shell( display );
        shell.setText( "SashForm Test" );
        shell.setLayout( new FillLayout() );

        // Create the ScrolledComposite to scroll horizontally and vertically
        ScrolledComposite sc = new ScrolledComposite( shell, SWT.H_SCROLL | SWT.V_SCROLL );

        Composite child = new Composite( sc, SWT.NONE );
        child.setLayout( new FillLayout() );

        new Label(child, SWT.BORDER).setText( "sdfdsf" );
        new Button( child, SWT.PUSH ).setText( "One" );
        new Button( child, SWT.PUSH ).setText( "Two" );

        child.setSize( 400, 400 );

        sc.setContent( child );

        shell.open();
        while( !shell.isDisposed() )
        {
            if( !display.readAndDispatch() )
            {
                display.sleep();
            }
        }
        display.dispose();

    }
}
