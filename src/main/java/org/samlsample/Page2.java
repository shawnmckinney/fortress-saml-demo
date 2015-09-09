/*
 * This is free and unencumbered software released into the public domain.
 */
package org.samlsample;


import com.googlecode.wicket.jquery.ui.form.button.IndicatingAjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.samlsample.control.FtIndicatingAjaxButton;


/**
 * Wicket Sample Page 2
 *
 * @author Shawn McKinney
 * @version $Rev$
 */
public class Page2 extends SamlSampleBasePage
{
    public Page2()
    {
        add( new Page2Form( "pageForm" ) );
    }

    /**
     * Page 2 Form
     */
    public class Page2Form extends Form
    {
        public Page2Form( String id )
        {
            super( id );
            add( new Label( "label2", "This is " + getUserId() + "'s Page2" ) );

            // Maps to Permission: obj: page2, operation: button1
            add( new FtIndicatingAjaxButton( "page2.button1" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target, Form form )
                {
                    logIt( target, "Page 2, Button 1 Pressed" );
                }
            } );

            // Maps to Permission: obj: page2, operation: button2
            add( new FtIndicatingAjaxButton( "page2.button2" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target, Form form )
                {
                    logIt( target, "Page 2, Button 2 Pressed" );
                }
            } );

            // Maps to Permission: obj: page2, operation: button3
            add( new FtIndicatingAjaxButton( "page2.button3" )
            {
                @Override
                protected void onSubmit( AjaxRequestTarget target, Form form )
                {
                    logIt( target, "Page 2, Button 3 Pressed" );
                }
            } );
        }
    }
}
