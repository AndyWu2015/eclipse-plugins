package rcpclock.start;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import rcpclock.Operation;


public class Activator implements BundleActivator
{

    @Override
    public void start( BundleContext context ) throws Exception
    {
        Dictionary<String,String> start = new Hashtable<String,String>();
        start.put( "name", "Start" );
        context.registerService( Operation.class , new StartOperation(), start);
        
        Dictionary<String,String> stop = new Hashtable<String,String>();
        stop.put( "name", "Stop" );
        context.registerService( Operation.class , new StopOperation(), stop);
    }

    @Override
    public void stop( BundleContext context ) throws Exception
    {
        
    }

}
