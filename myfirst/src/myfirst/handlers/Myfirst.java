package myfirst.handlers;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


public class Myfirst extends Plugin
{
    private static Myfirst plugin;
    
    public static Myfirst getDefault()
    {
        return plugin;
    }

    @Override
    public void start( BundleContext context ) throws Exception
    {
       
        super.start( context );
        
        plugin = this;
    }

    @Override
    public void stop( BundleContext context ) throws Exception
    {
        plugin = null;
        super.stop( context );
        
    }
    
    
}
