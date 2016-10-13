package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class CollapsibleWidgets
{

    public static void main( String[] args )
    {
        Display display = new Display();

        Shell shell = new Shell( display );

        shell.setLayout( new FillLayout() );

        Composite composite = new Composite( shell, SWT.NULL );

        composite.setLayout( new GridLayout() );
        composite.setLayoutData( new GridData( GridData.FILL_BOTH ) );

        FormToolkit toolkit = new FormToolkit( display );

        Form form = toolkit.createForm( composite );

        form.setText( "Custom Form Widgets Demo" );

        form.getBody().setLayout( new TableWrapLayout() );

        Section section =
            toolkit.createSection( form.getBody(), Section.DESCRIPTION | Section.TREE_NODE | Section.EXPANDED );

        section.setText( "This is the title" );

        toolkit.createCompositeSeparator( section );

        section.setDescription( "-= This is a description -=" );

        FormText text = toolkit.createFormText( section, false );

        text.setText( "This is a long text. The user can show or hide this text "
            + "by expanding or collapsing the expandable composite.", false, false );

        section.setClient( text );

        Button button = new Button( composite, SWT.PUSH );
        button.setText( "other composite" );

        shell.open();

        while( !shell.isDisposed() )
        {
            if( !display.readAndDispatch() )
                display.sleep();
        }
        display.dispose();
    }
}
