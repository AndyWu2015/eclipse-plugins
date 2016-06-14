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

import org.eclipse.swt.widgets.Display;

/**
 * @author Joye Luo
 */
public class ClockUtil
{

    public static String text;

    public static void setTime( final String date )
    {
        final Display display = Display.getDefault();

        if( ( display != null ) && ( !display.isDisposed() ) )
        {
            display.asyncExec( new Runnable()
            {

                @Override
                public void run()
                {
                    View.getLabel().setText( date );
                }
            } );
        }
    }

    public static String getTime()
    {
        final Display display = Display.getDefault();

        if( ( display != null ) && ( !display.isDisposed() ) )
        {
            display.syncExec( new Runnable()
            {

                @Override
                public void run()
                {
                    text = View.getLabel().getText();
                }
            } );
        }

        return text;
    }
}
