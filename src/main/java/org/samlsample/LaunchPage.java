/*
 * This is free and unencumbered software released into the public domain.
 */
package org.samlsample;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.wicket.markup.html.basic.Label;

/**
 * This Wicket Sample routes traffic here.  It displays list of page links at the top.
 *
 * @author Shawn McKinney
 * @version $Rev$
 */
public class LaunchPage extends SamlSampleBasePage
{
    private static final Logger LOG = LoggerFactory.getLogger( LaunchPage.class.getName() );
    public LaunchPage()
    {
        add(new Label("label1", "Welcome " + getUserId() + " you have access to the link(s) above."));
    }
}
