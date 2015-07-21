package com.example.e4.rcp.todo.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.example.e4.rcp.todo.dialogs.PasswordDialog;

public class EnterCredentialsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		PasswordDialog dialog = new PasswordDialog(shell);

		// get the new values from the dialog
		if (dialog.open() == Window.OK) {
			String user = dialog.getUser();
			String pw = dialog.getPassword();
			System.out.println(user);
			System.out.println(pw);
		}
		return null;
	}

	

}