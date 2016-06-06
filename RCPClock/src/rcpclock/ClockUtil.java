package rcpclock;

import org.eclipse.swt.widgets.Display;

public class ClockUtil
{
    public static String text;
    
    public static void setTime(final String date)
    {
        final Display display = Display.getDefault();

        if( ( display != null ) && ( !display.isDisposed() ) )
        {
            display.asyncExec( new Runnable()
            {
                @Override
                public void run()
                {
                    View2.label.setText( date );
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
                    text = View2.label.getText();
                }
            } );
        }
        return text;
    }
}
