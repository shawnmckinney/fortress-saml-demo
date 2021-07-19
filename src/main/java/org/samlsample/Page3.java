/*
 * This is free and unencumbered software released into the public domain.
 */
package org.samlsample;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.samlsample.control.FtIndicatingAjaxButton;


/**
 * Wicket Sample Page 3
 *
 * @author Shawn McKinney
 * @version $Rev$
 */
public class Page3 extends SamlSampleBasePage
{
    public Page3()
    {
        add( new Page3Form( "pageForm" ) );
    }

    /**
     * Page 3 Form
     */
    public class Page3Form extends Form
    {
        public Page3Form( String id )
        {
            super( id );
            add( new Label( "label3", "This is " + getUserId() + "'s Page3" ) );

            // Maps to Permission: obj: page3, operation: button1
            add( new FtIndicatingAjaxButton( "samlpage3.button1" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target )
                {
                    logIt( target, "Page 3, Button 1 Pressed" );
                }
            } );

            // Maps to Permission: obj: page3, operation: button2
            add( new FtIndicatingAjaxButton( "samlpage3.button2" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target )
                {
                    logIt( target, "Page 3, Button 2 Pressed" );
                }
            } );

            // Maps to Permission: obj: page3, operation: button3
            add( new FtIndicatingAjaxButton( "samlpage3.button3" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target )
                {
                    logIt( target, "Page 3, Button 3 Pressed" );
                }
            } );
        }
    }
}
