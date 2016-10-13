
package com.example.swt.widgets;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StackLayoutButtons
{

    public static void main( String[] args )
    {
        Display display = new Display();

        final Shell shell = new Shell( display );
        
        shell.setLayout( new FillLayout() );

        final StackLayout stackLayout = new StackLayout();
        
        final Composite container = new Composite( shell, SWT.BORDER );
        container.setLayout( stackLayout );
        
        Button button = new Button(shell, SWT.PUSH);
        button.setText( "change" );
        

        Composite composite1 = new Composite( container, SWT.NONE );
        composite1.setBackground(Display.getDefault().getSystemColor( SWT.COLOR_WHITE));
        
        Composite composite2 = new Composite( container, SWT.NONE );
        composite2.setBackground(Display.getDefault().getSystemColor( SWT.COLOR_BLUE));
        
        Composite composite3 = new Composite( container, SWT.NONE );
        composite3.setBackground(Display.getDefault().getSystemColor( SWT.COLOR_GREEN));
        
        final Composite[] composites = new Composite[3];
        composites[0] = composite1;
        composites[1] = composite2;
        composites[2] = composite3;
        
        button.addSelectionListener( new SelectionListener()
        {
            @Override
            public void widgetSelected( SelectionEvent e )
            {
                int number = new Random().nextInt(3);
                stackLayout.topControl = composites[number];
                container.layout();
            }
            
            @Override
            public void widgetDefaultSelected( SelectionEvent e )
            {
            }
        } );

        stackLayout.topControl = composite1;
        
        container.layout();

        shell.setSize( 450, 400 );
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
