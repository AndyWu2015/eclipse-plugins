
package com.liferay.ide.animatedwizardsample;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class AnimatedWizardSample extends Plugin
{
    private static AnimatedWizardSample plugin;

    public static AnimatedWizardSample getDefault()
    {
        return plugin;
    }

    @Override
    public void start( BundleContext context ) throws Exception
    {
        super.start( context );

        plugin = this;
    }

    @Override
    public void stop( BundleContext context ) throws Exception
    {
        plugin = null;
        super.stop( context );
    }
}
