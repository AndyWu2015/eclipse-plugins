package de.vogella.write2console;

import java.io.PrintStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


public class Write2ConsoleHandler extends AbstractHandler
{

    @Override
    public Object execute( ExecutionEvent event ) throws ExecutionException
    {
        MessageConsole myConsole = findConsole( "my console" );
        MessageConsoleStream out = myConsole.newMessageStream();
        out.println( "Hello from de.vogella.write2Console" );
        
        MessageConsole console = Write2ConsoleHandler.findConsole( "my console" );
        PrintStream ps = new PrintStream(console.newMessageStream());
        System.setOut( ps );
        System.out.println("is it changed the console ?");

        return null;
    }
    
    public static MessageConsole findConsole( String name )
    {
        ConsolePlugin plugin = ConsolePlugin.getDefault();
        IConsoleManager conMan = plugin.getConsoleManager();
        IConsole[] existing = conMan.getConsoles();
        for( int i = 0; i < existing.length; i++ )
            if( name.equals( existing[i].getName() ) )
                return (MessageConsole) existing[i];
        // no console found, so create a new one
        MessageConsole myConsole = new MessageConsole( name, null );
        conMan.addConsoles( new IConsole[] { myConsole } );
        return myConsole;
    }

}
