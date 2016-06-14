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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

/**
 * @author Joye Luo
 */
public class IOperationReader
{

    private static final String OPERATION_ID = "com.liferay.ide.training.clock.operation";

    private static Map<String, IOperation> map = new HashMap<String, IOperation>();

    public static IOperation get( String name )
    {
        return map.get( name );
    }

    public static void init()
    {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] config = registry.getConfigurationElementsFor( OPERATION_ID );

        try
        {
            for( IConfigurationElement e : config )
            {
                String name = e.getAttribute( "name" );

                final Object o = e.createExecutableExtension( "class" );

                if( o instanceof IOperation )
                {
                    map.put( name, (IOperation) o );
                }
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static List<String> getNames()
    {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] config = registry.getConfigurationElementsFor( OPERATION_ID );
        List<String> names = new ArrayList<String>();

        for( IConfigurationElement e : config )
        {
            String name = e.getAttribute( "name" );
            names.add( name );
        }

        return names;
    }

    public static void readOperation()
    {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] config = registry.getConfigurationElementsFor( OPERATION_ID );

        try
        {
            for( IConfigurationElement e : config )
            {
                final Object o = e.createExecutableExtension( "class" );
                if( o instanceof IOperation )
                {
                    executeExtension( o );
                }
            }
        }
        catch( CoreException e )
        {
            e.printStackTrace();
        }

    }

    public static void executeExtension( final Object o )
    {
        ISafeRunnable runnable = new ISafeRunnable()
        {

            @Override
            public void handleException( Throwable e )
            {
                System.out.println( "Exception in client" );
            }

            @Override
            public void run() throws Exception
            {
                ( (IOperation) o ).operate();
            }
        };
        SafeRunner.run( runnable );
    }
}
