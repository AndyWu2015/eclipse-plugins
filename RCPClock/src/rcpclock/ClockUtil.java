package rcpclock;

import java.util.List;

import org.eclipse.swt.widgets.Display;

public class ClockUtil
{
    public static String text;
    
    public static void setTime(final String date)
    {
/*        List<String> nameProperties = OperationReader.getNameProperties();
        
        for(String str : nameProperties)
        {
            System.out.println("------------------"+str);
        }*/
        
        final Display display = Display.getDefault();

        if( ( display != null ) && ( !display.isDisposed() ) )
        {
            display.asyncExec( new Runnable()
            {
                @Override
                public void run()
                {
                    View2.getLabel().setText( date );
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
                    text = View2.getLabel().getText();
                }
            } );
        }
        return text;
    }
}
