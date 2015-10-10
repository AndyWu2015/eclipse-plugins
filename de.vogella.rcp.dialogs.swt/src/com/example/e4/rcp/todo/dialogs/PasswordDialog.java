package com.example.e4.rcp.todo.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class PasswordDialog extends Dialog {
  private Text txtUser;
  private Text txtPassword;
  private String user = "";
  private String password = "";
  private TableViewer viewer;
  
  class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return new String[] { "One", "Two", "Three" };
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}

  public PasswordDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite container = (Composite) super.createDialogArea(parent);
    GridLayout layout = new GridLayout(2, false);
    layout.marginRight = 5;
    layout.marginLeft = 10;
    container.setLayout(layout);

    Label lblUser = new Label(container, SWT.NONE);
    lblUser.setText("User:");

    txtUser = new Text(container, SWT.BORDER);
    txtUser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
        1, 1));
    txtUser.setText(user);
    txtUser.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent e) {
        Text textWidget = (Text) e.getSource();
        String userText = textWidget.getText();
        user = userText;
      }
    });

    Label lblPassword = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel.horizontalIndent = 1;
    lblPassword.setLayoutData(gd_lblNewLabel);
    lblPassword.setText("Password:");

    txtPassword = new Text(container, SWT.BORDER| SWT.PASSWORD);
    txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
        false, 1, 1));
    txtPassword.setText(password);
    txtPassword.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent e) {
        Text textWidget = (Text) e.getSource();
        String passwordText = textWidget.getText();
        password = passwordText;
      }
    });
    //cteate tables
    viewer = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
    //viewer.setContentProvider(ArrayContentProvider.getInstance());
	viewer.setContentProvider(new ViewContentProvider());
	viewer.setLabelProvider(new ViewLabelProvider());
	viewer.setSorter(new NameSorter());
	//viewer.add("nihao");	
	//can't be null
	viewer.setInput(new Object());
	//viewer.add("nihao");
	final Table table = viewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true); 
	GridData data = new GridData( SWT.FILL, SWT.FILL, true, true );
	table.setLayoutData( data );
    
    return container;
  }

  // override method to use "Login" as label for the OK button
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, "Login", true);
    createButton(parent, IDialogConstants.CANCEL_ID,
        IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected Point getInitialSize() {
    return new Point(450, 300);
  }

  @Override
  protected void okPressed() {
    user = txtUser.getText();
    password = txtPassword.getText();
    super.okPressed();
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}