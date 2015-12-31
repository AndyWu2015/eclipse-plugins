
package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeMenuTest
{

    public static void main( String[] args )
    {
        Display display = Display.getDefault();
        Shell shell = new Shell( display );
        shell.setLayout( new FillLayout() );
        final Tree tree = new Tree( shell, SWT.V_SCROLL );
        TreeItem some = null;
        for( int i = 0; i < 5; i++ )
        {
            TreeItem item = new TreeItem( tree, SWT.NONE );
            item.setText( String.valueOf( i ) );
            some = item;
            for( int j = 0; j < 3; j++ )
            {
                TreeItem subItem = new TreeItem( item, SWT.NONE );
                subItem.setText( String.valueOf( i ) + " " + String.valueOf( j ) );
                //some = subItem ;
            }
        }

        tree.pack();
        
        System.out.println(tree.getItems().length);
        //some.dispose();
        System.out.println(tree.getItems().length);
        final TreeItem somefinal = some;
        Menu menu = new Menu( tree );
        MenuItem menuItem = new MenuItem( menu, SWT.NONE );
        menuItem.setText( "Print Element" );
        menuItem.addSelectionListener( new SelectionAdapter()
        {

            @Override
            public void widgetSelected( SelectionEvent event )
            {
                //delete the last node
                System.out.println( tree.getSelection()[0].getText() );
                System.out.println(tree.getItems().length);
                somefinal.dispose();
                System.out.println(tree.getItems().length);
            }
        } );
        tree.setMenu( menu );
        shell.pack();
        shell.open();
        while( !shell.isDisposed() )
        {
            if( !display.readAndDispatch() )
            {
                display.sleep();
            }
        }
    }
}
