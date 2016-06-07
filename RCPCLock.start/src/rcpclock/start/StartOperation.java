package rcpclock.start;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import rcpclock.ClockUtil;
import rcpclock.Operation;


public class StartOperation implements Operation
{

    //TODO make it start form current time in UI not from local real time
    
    Timer timeChanger = null;

    class ChangeTask extends TimerTask
    {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH );

        public void run()
        {
            Date date = new Date();
            String time = df.format( date );
            ClockUtil.setTime( time );
        }
    }

    @Override
    public void operate()
    {
        timeChanger = new Timer();
        timeChanger.schedule(new ChangeTask(), 0, 1000);
    }

}
