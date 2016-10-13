
package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class Next extends Composite
{

    private Text txtYouAreIn;

    public Next( Composite parent, int style )
    {
        super( parent, style );

        Button btnYay = new Button( parent, SWT.NONE );
        btnYay.setBounds( 43, 58, 75, 25 );
        btnYay.setText( "Yay" );

        txtYouAreIn = new Text( parent, SWT.BORDER );
        txtYouAreIn.setText( "You are in a new" );
        txtYouAreIn.setBounds( 173, 113, 115, 21 );
    }
}
