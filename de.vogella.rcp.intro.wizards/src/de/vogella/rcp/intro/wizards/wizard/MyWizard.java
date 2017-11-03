
package de.vogella.rcp.intro.wizards.wizard;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard
{
    protected MyPageOne one;
    protected MyPageTwo two;

    public MyWizard()
    {
        super();
        setNeedsProgressMonitor( true );
    }

    @Override
    public String getWindowTitle()
    {
        return "Export My Data";
    }
    
    

    @Override
    public void addPages()
    {
        one = new MyPageOne();
        two = new MyPageTwo();
        //the adding order will decide the showing order
        addPage( one );
        addPage( two );
    }

    @Override
    public boolean performFinish()
    {
        System.out.println( one.getText1() );
        System.out.println( two.getText1() );
        return true;
    }
}
