package myfirst.handlers;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public void start(BundleContext bundleContext) throws Exception {
	    
	    System.out.println("getLocation:"+Platform.getLocation());
	    System.out.println("getInstallLocation:"+Platform.getInstallLocation().getURL().getPath());
	    System.out.println("getInstanceLocation:"+Platform.getInstanceLocation().getURL().getPath());
	}

	public void stop(BundleContext bundleContext) throws Exception {
	}

}
