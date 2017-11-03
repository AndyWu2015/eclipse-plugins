package com.liferay.ide.animatedwizardsample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.oomph.util.UIUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Display;

/**
 * @author Eike Stepper
 */
//extracted
public abstract class Animator
{
  private final List<Resource> resources = new ArrayList<Resource>();

  private final Display display;

  public AnimatedCanvas canvas;

  private int width;

  private int height;

  private Font baseFont;

  public Animator(Display display)
  {
    this.display = display;
  }

  public final Display getDisplay()
  {
    return display;
  }

  public final AnimatedCanvas getCanvas()
  {
    return canvas;
  }

  public final int getWidth()
  {
    return width;
  }

  public final int getHeight()
  {
    return height;
  }

  public final Font getBaseFont()
  {
    return baseFont;
  }

  protected void init()
  {
    Font initialFont = getCanvas().getFont();
    FontData[] fontData = initialFont.getFontData();
    for (int i = 0; i < fontData.length; i++)
    {
      fontData[i].setHeight(16);
      fontData[i].setStyle(SWT.BOLD);
    }

    baseFont = new Font(display, fontData);
  }

  protected void dispose()
  {
    UIUtil.dispose(resources.toArray(new Resource[resources.size()]));
  }

  protected final Image loadImage( String name )
  {
      URL url = null;

      try
      {
          url  = AnimatedWizardSample.getDefault().getBundle().getEntry( "images/" + name );
      }
      catch( Exception e )
      {
      }

      ImageDescriptor imagedesc = ImageDescriptor.createFromURL( url );

      Image image = imagedesc.createImage();

      resources.add( image );

      return image;
  }

  protected final Color createColor(int r, int g, int b)
  {
    Display display = getDisplay();
    Color color = new Color(display, r, g, b);
    resources.add(color);
    return color;
  }

  protected final Font createFont(int pixelHeight)
  {
    return createFont(pixelHeight, 0);
  }

  protected final Font createFont(int pixelHeight, int pixelWidth, String... testStrings)
  {
    if (testStrings.length == 0)
    {
      pixelWidth = Integer.MAX_VALUE;
      testStrings = new String[] { "Ag" };
    }

    Display display = getDisplay();
    GC fontGC = new GC(display);

    try
    {
      FontData[] fontData = baseFont.getFontData();
      int fontSize = 40;
      while (fontSize > 0)
      {
        for (int i = 0; i < fontData.length; i++)
        {
          fontData[i].setHeight(fontSize);
          fontData[i].setStyle(SWT.BOLD);
        }

        Font font = new Font(display, fontData);
        fontGC.setFont(font);

        if (isFontSmallEnough(pixelHeight, pixelWidth, fontGC, testStrings))
        {
          resources.add(font);
          return font;
        }

        font.dispose();
        --fontSize;
      }

      throw new RuntimeException("Could not create font: " + pixelHeight);
    }
    finally
    {
      fontGC.dispose();
    }
  }

  private boolean isFontSmallEnough(int pixelHeight, int pixelWidth, GC fontGC, String[] testStrings)
  {
    for (String testString : testStrings)
    {
      Point extent = fontGC.stringExtent(testString);
      if (extent.y > pixelHeight || extent.x > pixelWidth)
      {
        return false;
      }
    }

    return true;
  }

  protected final void setSize(int width, int height)
  {
    this.width = width;
    this.height = height;
  }

  protected boolean onKeyPressed(KeyEvent e)
  {
    return false;
  }

  protected boolean onMouseMove(int x, int y)
  {
    return false;
  }

  protected boolean onMouseDown(int x, int y)
  {
    return false;
  }

  protected abstract boolean advance();

  protected abstract void paint(GC gc, Image buffer);

  public static Rectangle drawText(GC gc, double cX, double cY, String text)
  {
    return drawText(gc, cX, cY, text, 0);
  }

  public static Rectangle drawText(GC gc, double cX, double cY, String text, int box)
  {
    Point extent = gc.stringExtent(text);

    int x = (int)(cX - extent.x / 2);
    int y = (int)(cY - extent.y / 2);

    if (x < box)
    {
      x = box;
    }

    Rectangle rectangle = new Rectangle(x, y, extent.x, extent.y);

    if (box > 0)
    {
      rectangle.x -= box;
      rectangle.y -= box;
      rectangle.width += 2 * box;
      rectangle.height += 2 * box;

      gc.fillRectangle(rectangle);
    }

    gc.drawText(text, x, y, true);

    return rectangle;
  }

  public static Rectangle drawImage(GC gc, Image image, int cX, int cY)
  {
    Rectangle bounds = image.getBounds();

    cX -= bounds.width / 2;
    cY -= bounds.height / 2;

    gc.drawImage(image, cX, cY);

    return new Rectangle(cX, cY, bounds.width, bounds.height);
  }
}
