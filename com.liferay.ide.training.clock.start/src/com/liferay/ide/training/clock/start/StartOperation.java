/*******************************************************************************
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/

package com.liferay.ide.training.clock.start;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.liferay.ide.training.clock.ClockUtil;
import com.liferay.ide.training.clock.Flag;
import com.liferay.ide.training.clock.IOperation;

/**
 * @author Joye Luo
 */
public class StartOperation implements IOperation
{

    Timer timeChanger = null;

    class ChangeTask extends TimerTask
    {

        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );

        public void run()
        {
            if( Flag.getFlag() )
            {
                String UITime = ClockUtil.getTime();
                Date uitime;
                try
                {
                    uitime = df.parse( UITime );
                    Calendar rightNow = Calendar.getInstance();
                    rightNow.setTime( uitime );
                    rightNow.add( Calendar.SECOND, +1 );
                    Date dt1 = rightNow.getTime();
                    String time = df.format( dt1 );
                    ClockUtil.setTime( time );
                }
                catch( ParseException e )
                {
                    e.printStackTrace();
                }
            }
            else
            {
                timeChanger.cancel();
            }
        }
    }

    @Override
    public void operate()
    {
        Flag.startFlag();
        timeChanger = new Timer();
        timeChanger.schedule( new ChangeTask(), 0, 1000 );
    }

}
