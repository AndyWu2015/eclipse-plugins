package rcpclock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class View2 extends ViewPart {

	public static final String ID = "RCPClock.view2";

    private static Label label = null;
	
    public static Label getLabel()
    {
        return label;
    }

    SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH );
	
	@Override
	public void createPartControl(Composite parent) {

	    FillLayout  layout = new FillLayout();
		parent.setLayout(layout);

		label = new Label(parent, SWT.NONE);
		label.setSize(50, 500);

		Date date = new Date();
		String time = df.format(date);

		label.setText(time);

		/*Button button = new Button(parent, SWT.PUSH);
		button.setText("STOP");
		button.setSize(200, 100);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.getSource();
				String text = button.getText();
				if (text.equals("STOP")) {
					// System.out.println("Starte:"+timeChanger.getState());
					button.setText("START");
					timeChanger.cancel();
				} else {
					// System.out.println("Starte:"+timeChanger.getState());
					button.setText("STOP");
					// restart the thread
					timeChanger = new Timer();
					timeChanger.schedule(new ChangeTask(), 0, 1000);
				}
			}
		});*/
		
		OperationReader.init();
		
		List<String> names = OperationReader.getNames();
		for(final String name : names)
		{
		    Button button = new Button(parent , SWT.PUSH);
		    button.setText( name );
		    button.addSelectionListener( new SelectionAdapter()
            {
                @Override
                public void widgetSelected( SelectionEvent e )
                {
                    OperationReader.executeExtension( OperationReader.get( name ) );
                }
            } );
		}
	}

	public void setFocus() {

	}
}
