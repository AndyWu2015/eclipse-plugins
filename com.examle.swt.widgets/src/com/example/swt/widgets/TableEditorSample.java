
package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableEditorSample
{

    public static void main( String[] args )
    {
        Display display = new Display();
        Shell shell = new Shell( display );
        shell.setText( "Text Table Editor" );

        shell.setLayout( new FillLayout() );

        Table table = new Table( shell, SWT.SINGLE | SWT.FULL_SELECTION | SWT.HIDE_SELECTION );
        table.setHeaderVisible( true );
        table.setLinesVisible( true );

        for( int i = 0; i < 5; i++ )
        {
            TableColumn column = new TableColumn( table, SWT.CENTER );
            column.setText( "Column " + ( i + 1 ) );
            column.pack();
        }
        // Create five table editors for color
        TableEditor[] colorEditors = new TableEditor[5];

        // Create five buttons for changing color
        Button[] colorButtons = new Button[5];

        for( int i = 0; i < 5; i++ )
        {
            final TableItem item = new TableItem( table, SWT.NONE );
            // Create the editor and button
            colorEditors[i] = new TableEditor( table );
            colorButtons[i] = new Button( table, SWT.PUSH );

            // Set attributes of the button
            colorButtons[i].setText( "Color..." );
            colorButtons[i].computeSize( SWT.DEFAULT, table.getItemHeight() );

            // Set attributes of the editor
            colorEditors[i].grabHorizontal = true;
            colorEditors[i].minimumHeight = colorButtons[i].getSize().y;
            colorEditors[i].minimumWidth = colorButtons[i].getSize().x;

            // Set the editor for the first column in the row
            colorEditors[i].setEditor( colorButtons[i], item, 0 );
        }
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
