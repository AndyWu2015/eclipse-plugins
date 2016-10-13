/*
 * Copyright (c) 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */

package com.liferay.ide.animatedwizardsample;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Eike Stepper
 */

//this class is a container and trigger to redraw the animator
public class AnimatedCanvas extends Canvas
{
    private static final int DEFAULT_TIMER_INTERVAL = 10;

    private final Runnable runnable = new Runnable()
    {
        public void run()
        {
            doRun();
        }
    };

    private final List<Animator> animators = new ArrayList<Animator>();

    private int timerInterval;

    private Point shellMoveStart;

    //default constructor with default interval value
    public AnimatedCanvas( Composite parent, int style )
    {
        this( parent, style, DEFAULT_TIMER_INTERVAL );
    }

    public AnimatedCanvas( Composite parent, int style, int timerInterval )
    {
        super( parent, style | SWT.DOUBLE_BUFFERED );

        Display display = getDisplay();

        setBackground( display.getSystemColor( SWT.COLOR_WHITE ) );

        //focus on and off will redraw the canvas
        addFocusListener( new FocusListener()
        {
            public void focusGained( FocusEvent e )
            {
                redraw();
            }

            public void focusLost( FocusEvent e )
            {
                redraw();
            }
        } );

        //receive paing event and will call paint method in each animator
        addPaintListener( new PaintListener()
        {
            public void paintControl( PaintEvent event )
            {
                doPaint( event.gc );
            }
        } );

        //pass the key and mouse event to child animators
        addKeyListener( new KeyAdapter()
        {
            @Override
            public void keyPressed( KeyEvent e )
            {
                if( !onKeyPressed( e ) )
                {
                    super.keyPressed( e );
                }
            }
        } );

        addMouseTrackListener( new MouseTrackAdapter()
        {
            @Override
            public void mouseExit( MouseEvent e )
            {
                onMouseMove( Integer.MIN_VALUE, Integer.MIN_VALUE );
            }
        } );

        addMouseMoveListener( new MouseMoveListener()
        {

            public void mouseMove( MouseEvent e )
            {
                onMouseMove( e.x, e.y );
            }
        } );

        addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseDown( MouseEvent e )
            {
                if( e.button == 1 )
                {
                    onMouseDown( e.x, e.y );
                }
            }

            @Override
            public void mouseUp( MouseEvent e )
            {
                if( shellMoveStart != null )
                {
                    shellMoveStart = null;
                }
            }
        } );

        //it is a bug , i added this call here to set the timerinterval
        setTimerInterval(timerInterval);
        display.timerExec( timerInterval, runnable );
    }

    public final Animator[] getAnimators()
    {
        synchronized( animators )
        {
            return animators.toArray( new Animator[animators.size()] );
        }
    }

    public final void addAnimator( Animator animator )
    {
        synchronized( animators )
        {
            animator.canvas = this;
            animator.init();
            animators.add( animator );
        }
    }

    public final void removeAnimator( Animator animator )
    {
        synchronized( animators )
        {
            animators.remove( animator );
            animator.dispose();
            animator.canvas = null;
        }
    }

    public final int getTimerInterval()
    {
        return timerInterval;
    }

    public final void setTimerInterval( int timerInterval )
    {
        this.timerInterval = timerInterval;
    }

    public void cover( GC gc, int alpha )
    {
        gc.setBackground( getDisplay().getSystemColor( SWT.COLOR_WHITE ) );
        gc.setAlpha( alpha );
        gc.fillRectangle( getBounds() );
        gc.setAlpha( 255 );
    }

    @Override
    public synchronized void dispose()
    {
        getDisplay().timerExec( -1, runnable );

        for( Animator animator : getAnimators() )
        {
            animator.dispose();
        }

        super.dispose();
    }

    protected boolean onKeyPressed( KeyEvent e )
    {
        Animator[] animators = getAnimators();
        for( int i = animators.length - 1; i >= 0; --i )
        {
            Animator animator = animators[i];
            if( animator.onKeyPressed( e ) )
            {
                return true;
            }
        }

        return false;
    }

    protected void onMouseMove( int x, int y )
    {
        if( shellMoveStart != null )
        {
            //for drag and drop the whole shell
            Shell shell = getShell();
            Point location = shell.getLocation();
            location.x += x - shellMoveStart.x;
            location.y += y - shellMoveStart.y;
            shell.setLocation( location );
        }

        Animator[] animators = getAnimators();
        
        for( int i = animators.length - 1; i >= 0; --i )
        {
            Animator animator = animators[i];
            if( animator.onMouseMove( x, y ) )
            {
                return;
            }
        }
    }

    protected void onMouseDown( int x, int y )
    {
        Animator[] animators = getAnimators();
        for( int i = animators.length - 1; i >= 0; --i )
        {
            Animator animator = animators[i];
            if( animator.onMouseDown( x, y ) )
            {
                return;
            }
        }

        shellMoveStart = new Point( x, y );
    }

    protected synchronized void doRun()
    {
        if( isDisposed() )
        {
            return;
        }

        boolean needsRedraw = false;

        for( Animator animator : getAnimators() )
        {
            if( animator.advance() )
            {
                needsRedraw = true;
            }
        }

        if( needsRedraw )
        {
            redraw();
        }
        else
        {
            scheduleRun();
        }
    }

    protected void doPaint( GC canvasGC )
    {
        Image buffer = new Image( getDisplay(), getBounds() );

        GC bufferGC = new GC( buffer );

        bufferGC.setAdvanced( true );
        bufferGC.setBackground( canvasGC.getBackground() );
        bufferGC.fillRectangle( buffer.getBounds() );

        for( Animator animator : getAnimators() )
        {
            bufferGC.setTextAntialias( SWT.ON );

            animator.paint( bufferGC, buffer );
        }

        canvasGC.drawImage( buffer, 0, 0 );

        bufferGC.dispose();
        buffer.dispose();

        if( !isFocusControl() )
        {
            //when lose focus , make the alpha to 200
            cover( canvasGC, 0 );
        }

        scheduleRun();
    }

    //re-trigger the redraw event
    private void scheduleRun()
    {
        getDisplay().timerExec( timerInterval, runnable );
    }
}
