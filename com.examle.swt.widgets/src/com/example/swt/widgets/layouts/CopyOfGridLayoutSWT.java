
package com.example.swt.widgets.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CopyOfGridLayoutSWT
{

    public static void main( String[] args )
    {
        Display display = new Display();

        Shell shell = new Shell( display );
        
        shell.setLayout( new GridLayout(1,false) );

        Composite buttonContainer = new Composite( shell, SWT.BORDER );

        buttonContainer.setLayout( new GridLayout( 5, false ) );
        GridData buttonContainerData = new GridData( GridData.FILL_HORIZONTAL );

        buttonContainer.setLayoutData( buttonContainerData );

        Label label = new Label( buttonContainer, SWT.RIGHT );

        label.setText( "Converted Project Location:" );

        Text projectLocation = new Text( buttonContainer, SWT.BORDER );

        GridData textData = new GridData( GridData.FILL_HORIZONTAL );

        textData.grabExcessHorizontalSpace = true;
        textData.horizontalSpan = 2;

        projectLocation.setLayoutData( textData );
        
        Composite buttonLayout = new Composite(buttonContainer , SWT.BORDER);
        buttonLayout.setLayout( new GridLayout(2,false) );

        Button selectButton = new Button( buttonLayout, SWT.PUSH );

        selectButton.setText( "run" );

        GridData buttonGridData = new GridData( SWT.RIGHT, SWT.CENTER, true, true );

        //buttonGridData.widthHint = 130;
        //buttonGridData.heightHint = 35;

        selectButton.setLayoutData( buttonGridData );

        Button clearButton = new Button( buttonLayout, SWT.PUSH );

        clearButton.setText( "clear" );

        clearButton.setLayoutData( buttonGridData );

        shell.pack();
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
