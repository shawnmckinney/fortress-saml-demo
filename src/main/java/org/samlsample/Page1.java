/*
 * This is free and unencumbered software released into the public domain.
 */
package org.samlsample;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.samlsample.control.FtIndicatingAjaxButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Fortress Saml Wicket Sample Page 1
 *
 * @author Shawn McKinney
 * @version $Rev$
 */
public class Page1 extends SamlSampleBasePage
{
    private static final Logger LOG = LoggerFactory.getLogger( Page1.class.getName() );

    public Page1()
    {
        add( new Page1Form( "pageForm" ) );
    }

    /**
     * Page 1 Form
     */
    public class Page1Form extends Form
    {
        public Page1Form( String id )
        {
            super( id );
            add( new Label( "label1", "This is " + getUserId() + "'s Page1" ) );

            // Maps to Permission: obj: page1, operation: button1
            add( new FtIndicatingAjaxButton( "samlpage1.button1" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target )
                {
                    logIt( target, "Page 1, Button 1 Pressed" );
                }
            } );

            // Maps to Permission: obj: page1, operation: button2
            add( new FtIndicatingAjaxButton( "samlpage1.button2" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target )
                {
                    logIt( target, "Page 1, Button 2 Pressed" );
                }
            } );

            // Maps to Permission: obj: page1, operation: button3
            add( new FtIndicatingAjaxButton( "samlpage1.button3" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target )
                {
                    logIt( target, "Page 1, Button 3 Pressed" );
                }
            } );
        }
    }
}
