
package com.example.swt.widgets;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class CompositeUISample
{

    public static void main( String[] args )
    {
        CompositeUISample sample = new CompositeUISample();

        sample.render();
    }

    private TreeViewer viewer;
    
    public void render()
    {
        Display display = new Display();
        
        Shell shell = new Shell( display );

        shell.setText( "title" );

        shell.setLayout( new FillLayout() );
        
        shell.setSize( 400, 400 );
        
        
        
        SashForm sashForm = new SashForm( shell, SWT.HORIZONTAL | SWT.H_SCROLL );
        
        ScrolledComposite leftContainer = new ScrolledComposite(sashForm, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        
        Composite left = new Composite(leftContainer,SWT.NONE);
        
        left.setLayout( new GridLayout(1,false) );
        left.setLayoutData( new GridData(GridData.FILL_BOTH) );
        
        
        leftContainer.setLayout( new FillLayout() );
        
        leftContainer.setMinSize( 300, 300 );
        leftContainer.setExpandHorizontal(true);
        leftContainer.setExpandVertical(true);
        
        leftContainer.setContent( left );
        
        Composite right = new Composite(sashForm,SWT.NONE);
        
        right.setLayout( new FillLayout() );
        
        Label leftLabel = new Label( left, SWT.BORDER );
        leftLabel.setText( "left ccccccccccccccccccc" );
        
        
        Label rightLabel = new Label( right, SWT.BORDER );
        rightLabel.setText( "right ccccccccccccccccccc" );
        

        viewer = new TreeViewer(left, SWT.H_SCROLL | SWT.V_SCROLL);
        
        viewer.getTree().setLayout( new GridLayout(1,false) );
        viewer.getTree().setLayoutData( new GridData(GridData.FILL_BOTH) );

        viewer.setContentProvider(new ViewContentProvider());
        viewer.setLabelProvider(new ViewLabelProvider());
        viewer.setInput(File.listRoots());
        

        shell.open();
        while( !shell.isDisposed() )
        {
            if( !display.readAndDispatch() )
                display.sleep();
        }
        display.dispose();
    }

      class ViewContentProvider implements ITreeContentProvider {
        public void inputChanged(Viewer v, Object oldInput, Object newInput) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public Object[] getElements(Object inputElement) {
          return (File[]) inputElement;
        }

        @Override
        public Object[] getChildren(Object parentElement) {
          File file = (File) parentElement;
          return file.listFiles();
        }

        @Override
        public Object getParent(Object element) {
          File file = (File) element;
          return file.getParentFile();
        }

        @Override
        public boolean hasChildren(Object element) {
          File file = (File) element;
          if (file.isDirectory()) {
            return true;
          }
          return false;
        }

      }

      class ViewLabelProvider extends StyledCellLabelProvider {
        @Override
        public void update(ViewerCell cell) {
          Object element = cell.getElement();
          StyledString text = new StyledString();
          File file = (File) element;
          if (file.isDirectory()) {
            text.append(getFileName(file));
            String[] files = file.list();
            if (files != null) {
              text.append(" (" + files.length + ") ",
                  StyledString.COUNTER_STYLER);
            }
          } else {
            text.append(getFileName(file));
          }
          cell.setText(text.toString());
          cell.setStyleRanges(text.getStyleRanges());
          super.update(cell);

        }

        private String getFileName(File file) {
          String name = file.getName();
          return name.isEmpty() ? file.getPath() : name;
        }
      }
}
