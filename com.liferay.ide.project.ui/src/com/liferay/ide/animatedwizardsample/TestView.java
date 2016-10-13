package com.liferay.ide.animatedwizardsample;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


public class TestView extends ViewPart
{
    private GearAnimator animator;
    
    private AnimatedCanvas canvas;
    
    private static final boolean TEST_OVERLAYS = false;
    
    public final GearAnimator getAnimator()
    {
      return animator;
    }
    
    @Override
    public void createPartControl( Composite parent )
    {
      //  parent.setLayout(new FillLayout());

        canvas = new AnimatedCanvas(parent, SWT.NONE);
        
        animator = new GearAnimator(parent.getDisplay())
        {
          @Override
          protected boolean onKeyPressed(KeyEvent e)
          {
            if (TEST_OVERLAYS)
            {
              GearAnimator animator = getAnimator();
              Page page = animator.getSelectedPage();

              if (page instanceof ImagePage)
              {
                if (e.keyCode == SWT.ARROW_RIGHT)
                {
                  animator.updateOverlay(1, 0);
                  return true;
                }

                if (e.keyCode == SWT.ARROW_LEFT)
                {
                  animator.updateOverlay(-1, 0);
                  return true;
                }

                if (e.keyCode == SWT.ARROW_DOWN)
                {
                  animator.updateOverlay(0, 1);
                  return true;
                }

                if (e.keyCode == SWT.ARROW_UP)
                {
                  animator.updateOverlay(0, -1);
                  return true;
                }
              }
            }

            return super.onKeyPressed(e);
          }

          @Override
          protected boolean shouldShowOverlay()
          {
            if (TEST_OVERLAYS)
            {
              return true;
            }

            return super.shouldShowOverlay();
          }
        };

        //animator.addListener(this);

        getCanvas().addAnimator(animator);



    }

    @Override
    public void setFocus()
    {

    }

    public final AnimatedCanvas getCanvas()
    {
      return canvas;
    }
}
