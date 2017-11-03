package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DashboardView
{

    protected Shell shell;
    private Composite composite;
    private Next next;
    private FormLayout layout;

    public static void main( String[] args )
    {
        try
        {
            DashboardView window = new DashboardView();
            window.open();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void open()
    {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while( !shell.isDisposed() )
        {
            if( !display.readAndDispatch() )
            {
                display.sleep();
            }
        }
    }

    protected void createContents()
    {
        shell = new Shell();
        shell.setSize( 572, 390 );
        shell.setText( "SWT Application" );
        layout = new FormLayout();
        shell.setLayout( layout );

        composite = new Composite( shell, SWT.NONE );
        composite.setBackground(Display.getDefault().getSystemColor( SWT.COLOR_WHITE));
        FormData fd_composite_1 = new FormData();
        fd_composite_1.top = new FormAttachment( 0, 10 );
        fd_composite_1.left = new FormAttachment( 0, 10 );
        fd_composite_1.bottom = new FormAttachment( 0, 299 );
        fd_composite_1.right = new FormAttachment( 0, 546 );
        composite.setLayoutData( fd_composite_1 );
        
        next = new Next( shell, SWT.NONE );
        
        composite.setVisible(true);
        next.setVisible(false);
        next.redraw();
        composite.redraw();

        Button btnNewButton = new Button( shell, SWT.NONE );

        btnNewButton.addSelectionListener( new SelectionAdapter()
        {
            @Override
            public void widgetSelected( SelectionEvent e )
            {
                composite.setVisible(!composite.isVisible());
                next.setVisible(!next.isVisible());
            }
        } );

        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.bottom = new FormAttachment( 100, -10 );
        fd_btnNewButton.right = new FormAttachment( composite, 0, SWT.RIGHT );
        
        btnNewButton.setLayoutData( fd_btnNewButton );
        btnNewButton.setText( "New Button" );

    }
}
