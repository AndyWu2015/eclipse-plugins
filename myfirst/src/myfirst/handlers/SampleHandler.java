package myfirst.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;



/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@SuppressWarnings( "restriction" )
    public Object execute(ExecutionEvent event) throws ExecutionException {
		/*IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		//test IProject
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("test");
		if(project == null){
			System.out.println("test project not found");
		}else{
			System.out.println(project.getName());
		}
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint point = registry.getExtensionPoint("org.eclipse.ui.views");
        if (point == null) 
        	return null;
        IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++)
        {
        	//System.out.println(extensions[i].getContributor().getName());
        }
        
		MessageDialog.openInformation(window.getShell(),"Myfirst","Hello, Eclipse world");*/
	    
	    /*IRunnableWithProgress op = new IRunnableWithProgress() {
	        public void run(IProgressMonitor monitor) {
	           System.out.println("--------running----------");
	        }
	     };
	     IWorkbench wb = PlatformUI.getWorkbench();
	     IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
	     Shell shell = win != null ? win.getShell() : null;
	     try
        {
            new ProgressMonitorDialog(shell).run(true, true, op);
        }
        catch( InvocationTargetException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
	    
	    /*IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
	    
	    final IProject project = projects[1];
	    
	    final IPath projectPath;
	    try
        {
            projectPath = project.getLocation().append( ".project" );
            final String parentPath = projectPath.toFile().getAbsolutePath();
            
            project.accept( new IResourceVisitor()
            {
                
                @Override
                public boolean visit( IResource resource ) throws CoreException
                {
                    IPath projectLoc = resource.getLocation().append( ".project" );

                    File file = projectLoc.toFile();
                    
                    String path = file.getAbsolutePath();
                    
                    if( file.exists() && !path.equals( parentPath ) )
                    {
                        System.out.println("find---------"+resource.getName());
                        return false;
                    }
                    
                    System.out.println( resource.getProject().getName() + ":" + resource.getName() + ":" +
                                    resource.getLocation().toPortableString() );
                    return true;
                }
            } );
        }
        catch( CoreException e )
        {
        }*/
	    
/*	    Job job = new WorkspaceJob( "get latest com.liferay.blade.cli.jar..." )
        {

            @Override
            public IStatus runInWorkspace( IProgressMonitor monitor ) throws CoreException
            {
                try
                {
                    Thread.sleep( 2000 );
                }
                catch( InterruptedException e )
                {
                    e.printStackTrace();
                }
                return new Status(IStatus.OK , "sdsd","sad");
            }
            
        };

        try
        {
            PlatformUI.getWorkbench().getProgressService().showInDialog( Display.getDefault().getActiveShell(), job );
        }
        catch( Exception e )
        {
        }

        job.setUser( true );
        job.schedule();*/
	    
	    //


		return null;
	}
}
