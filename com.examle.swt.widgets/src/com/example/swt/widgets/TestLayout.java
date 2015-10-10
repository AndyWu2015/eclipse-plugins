package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TestLayout extends AbstractExample{
    private Label infoLabel;
    private Text usernameText;
    private Text passwordText;
    private Combo roleCombo;
    
    public static void main(String[] args) {
        new TestLayout().run();
    }
    
    
    
    
    public void todo(Shell shell) {
        Group testGroup = new Group(shell,SWT.NONE);
        testGroup.setText("User Login");
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 30;
        layout.marginHeight = 10;
        testGroup.setLayout(layout);
        testGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); 
        {
            Composite composite = new Composite(testGroup,SWT.NONE);
            GridLayout layoutComposite = new GridLayout();
            layoutComposite.numColumns = 2;
            layoutComposite.marginHeight = 1;
            composite.setLayout(layoutComposite);
            composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,2));
            
            infoLabel = new Label(composite,SWT.NONE);
            infoLabel.setText("请输入用户名 密码");
            infoLabel.setLayoutData(new GridData(GridData.FILL_BOTH));
            infoLabel.setAlignment(SWT.RIGHT);
        }
        {
            Label usernameLabel = new Label(testGroup,SWT.NONE);
            usernameLabel.setText("username:");
            
            usernameText = new Text(testGroup,SWT.BORDER);
            usernameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        }
        {
            Label passwordLabel = new Label(testGroup,SWT.NONE);
            passwordLabel.setText("password:");
            
            passwordText = new Text(testGroup,SWT.BORDER | SWT.PASSWORD);
            passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        }
        {
            Label roleLabel = new Label(testGroup,SWT.NONE);
            roleLabel.setText("role:");
            
            roleCombo = new Combo(testGroup,SWT.DROP_DOWN);
            roleCombo.setItems(new String[]{"Admin","custom"});
            roleCombo.select(1);
        }
        {
            new Label(testGroup,SWT.NONE);
            
            Button rememberPWBtn = new Button(testGroup,SWT.CHECK);
            rememberPWBtn.setText("记住密码");
        }
        {
            new Label(testGroup,SWT.NONE);
            
            Button autoLoginBtn = new Button(testGroup,SWT.CHECK);
            autoLoginBtn.setText("自动登录");
        }
        {
            new Label(testGroup,SWT.NONE);
            
            Button loginBtn = new Button(testGroup,SWT.PUSH);
            loginBtn.setText("登录");
            
            loginBtn.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent evt){
                    infoLabel.setText("登陆成功");
                    
                    usernameText.setText("");
                    usernameText.setEnabled(false);
                    
                    passwordText.setText("");
                    passwordText.setEnabled(false);
                    
                    roleCombo.setEnabled(false);
                }
            });
        }
    }
}
