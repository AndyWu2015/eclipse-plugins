package rcpclock.start;

import rcpclock.ClockUtil;
import rcpclock.Operation;


public class StopOperation implements Operation
{
    
    //TODO change the stop to reset to some date
    @Override
    public void operate()
    {
        ClockUtil.setTime( "stop" );
    }

}
