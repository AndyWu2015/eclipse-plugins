/*******************************************************************************
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/

package com.liferay.ide.training.clock;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Joye Luo
 */
public class View extends ViewPart
{

    public static final String ID = "com.liferay.ide.training.clock.view";

    private static Label label = null;

    public static Label getLabel()
    {
        return label;
    }

    SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH );

    @Override
    public void createPartControl( Composite parent )
    {

        FillLayout layout = new FillLayout();
        parent.setLayout( layout );

        label = new Label( parent, SWT.NONE );
        label.setSize( 50, 500 );
        label.setText( "1994-03-20 17:50:32" );

        IOperationReader.init();

        List<String> names = IOperationReader.getNames();
        for( final String name : names )
        {
            Button button = new Button( parent, SWT.PUSH );
            button.setText( name );
            button.addSelectionListener( new SelectionAdapter()
            {

                @Override
                public void widgetSelected( SelectionEvent e )
                {
                    IOperationReader.executeExtension( IOperationReader.get( name ) );
                }
            } );
        }
    }

    public void setFocus()
    {

    }
}
