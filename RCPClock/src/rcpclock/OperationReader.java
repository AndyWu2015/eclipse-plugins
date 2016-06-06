package rcpclock;

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

public class OperationReader {

	private static final String OPERATION_ID = "RCPClock.operation";
	
	private static Map<String , Operation> map =  new HashMap<String , Operation>();

	
	public static Operation get(String name)
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
                
                if(o instanceof Operation)
                {
                    map.put( name, (Operation)o );
                }
            }
        }
        catch(Exception e)
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
                if( o instanceof Operation )
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
    
    public static void executeExtension(final Object o) {
        ISafeRunnable runnable = new ISafeRunnable() {
            @Override
            public void handleException(Throwable e) {
                System.out.println("Exception in client");
            }

            @Override
            public void run() throws Exception {
                ((Operation) o).operate();
            }
        };
        SafeRunner.run(runnable);
    }

    /*
	private void evaluate(IExtensionRegistry registry) {
		IConfigurationElement[] config = registry
				.getConfigurationElementsFor(IGREETER_ID);
		try {
			for (IConfigurationElement e : config) {
				//System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGreeter) {
					executeExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void executeExtension(final Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}

			@Override
			public void run() throws Exception {
				((IGreeter) o).greet();
			}
		};
		SafeRunner.run(runnable);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		execute(registry);
		return null;
	}*/
}