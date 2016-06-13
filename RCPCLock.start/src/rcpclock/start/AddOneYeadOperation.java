package rcpclock.start;

import rcpclock.ClockUtil;
import rcpclock.Operation;


public class AddOneYeadOperation implements Operation
{

    @Override
    public void operate()
    {
        String date = ClockUtil.getTime();
        //TODO calculate one year result and move it to a new plugin
        String result = null;
        
        ClockUtil.setTime( result );
        
    }

}
