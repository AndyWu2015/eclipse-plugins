package com.example.swt.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SWTUITest {
	
	public static Button btn = null;
	
	public static int count = 10000;
	
	public void run(){
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(500, 375);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout());
		btn = new Button(shell, SWT.NULL);
		btn.setText("shit");
		registerAction();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		shell.dispose();
		display.dispose();
	}

	public static void main(String[] args) {
		SWTUITest obj = new SWTUITest();
		obj.run();
	}
	private void registerAction() {
		btn.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseDown(MouseEvent e) {
				methodC();
			}
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
	}
	/**
	 * 持续的跑动, 打印线程的名称, 注意拖拽不动, 界面死掉, 直到跑完
	 */
	private static void methodA() {
		for (int i = 0; i < count; i++) {
			//Thread.currentThread().sleep(300);
			System.out.println("MethodA:" + Thread.currentThread().getName());
			btn.setText(i + "");
		}
	}
	
	private static void methodB() {  
	    Thread t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            for (int i = 0; i < count; i++) {  
	                //haveArest(300);  
	                System.out.println("MethodB:"  
	                        + Thread.currentThread().getName());  
	                btn.setText(i + "");  
	            }  
	        }  
	    });  
	    t.start();  
	}  
	
	private static void methodC() {  
	    Thread t = new Thread(new Runnable() {  
	        @Override  
	        public void run() {  
	            for (int i = 0; i < count; i++) {  
	                System.out.println("MethodB Thread:"  
	                        + Thread.currentThread().getName());  
	              
	                haveArest(300);  
	                final Display display = Display.getDefault();  
	                final String s = i + "";  
	                if ((display != null) && (!display.isDisposed())) {  
	                    display.asyncExec(new Runnable() {  
	                        @Override  
	                        public void run() {  
	                            System.out.println("MethodB Thread asyncExec:"  
	                                    + Thread.currentThread().getName());  
	                            btn.setText(s);  
	                        }  
	                    });  
	                }  
	            }  
	        }  
	    });  
	    t.start();  
	}  
	  
	private static void haveArest(int sleepTime)  
	{  
	    try {  
	        Thread.sleep(sleepTime);  
	    } catch (InterruptedException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	}  
}
