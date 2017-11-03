package com.example.resourcelistener;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		ResourcesPlugin.getWorkspace().addResourceChangeListener( new IResourceChangeListener()
        {
            @Override
            public void resourceChanged( IResourceChangeEvent event )
            {
                System.out.println("eventType: "+event.getType());
                
                if(event.getType() == IResourceChangeEvent.PRE_DELETE)
                {
                    IProject project = (IProject)event.getResource();
                    
                    IFolder folder = project.getFolder( "test" );
                    
                    System.out.println(folder.exists());
                }
                
                if(event.getType() == IResourceChangeEvent.POST_CHANGE )
                {
                    try
                    {
                        event.getDelta().accept(new DeltaPrinter());
                    }
                    catch( CoreException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        }, IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE
        | IResourceChangeEvent.PRE_BUILD | IResourceChangeEvent.POST_BUILD
        | IResourceChangeEvent.POST_CHANGE | IResourceChangeEvent.PRE_REFRESH);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	class DeltaPrinter implements IResourceDeltaVisitor {
	    public boolean visit(IResourceDelta delta) {
	       IResource res = delta.getResource();
	       switch (delta.getKind()) {
	          case IResourceDelta.ADDED:
	             System.out.print("Resource ");
	             System.out.print(res.getFullPath());
	             System.out.println(" was added.");
	             break;
	          case IResourceDelta.REMOVED:
	             System.out.print("Resource ");
	             System.out.print(res.getFullPath());
	             System.out.println(" was removed.");
	             break;
	          case IResourceDelta.CHANGED:
	             System.out.print("Resource ");
	             System.out.print(res.getFullPath());
	             System.out.println(" has changed.");
	             break;
	       }
	       return true; // visit the children
	    }
	 }
}
