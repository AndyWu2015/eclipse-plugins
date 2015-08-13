package rcpclock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	public static final String ID = "RCPClock.view";

	public Label label = null;

	/**
	 * the thread that change the Libel every every second
	 * 
	 * @author liferay
	 *
	 */
	class TimeChanger extends Thread {
		// the state of the thread for self stop
		private boolean isRun = true;

		public void stopThread(boolean isRun) {
			this.isRun = !isRun;
		}

		@Override
		public void run() {
			while (isRun) {
				final Display display = Display.getDefault();
				if ((display != null) && (!display.isDisposed())) {
					display.asyncExec(new Runnable() {
						@Override
						public void run() {
							SimpleDateFormat df = new SimpleDateFormat(
									"hh:mm:ss a", Locale.ENGLISH);
							Date date = new Date();
							String time = df.format(date);
							label.setText(time);
							// System.out.println("change:" + time);
						}
					});
				}
				// sleep 1s
				haveArest(1000);
			}
		}
	}

	TimeChanger timeChanger = null;

	public void createPartControl(Composite parent) {

		GridLayout layout = new GridLayout();
		parent.setLayout(layout);
		layout.numColumns = 2;

		label = new Label(parent, SWT.NONE);
		label.setSize(50, 500);

		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
		Date date = new Date();
		String time = df.format(date);
		label.setText(time);

		Button button = new Button(parent, SWT.PUSH);
		button.setText("STOP");
		button.setSize(50, 150);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.getSource();
				String text = button.getText();
				if (text.equals("STOP")) {
					// System.out.println("Starte:"+timeChanger.getState());
					button.setText("START");
					timeChanger.stopThread(true);
				} else {
					// System.out.println("Starte:"+timeChanger.getState());
					button.setText("STOP");
					// restart the thread
					timeChanger = new TimeChanger();
					timeChanger.start();
				}
			}
		});
		GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		button.setLayoutData(gd);
		timeChanger = new TimeChanger();
		timeChanger.start();
	}

	// the method for sleep
	private static void haveArest(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setFocus() {

	}
}