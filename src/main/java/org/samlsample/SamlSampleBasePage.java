/*
 * This is free and unencumbered software released into the public domain.
 */
package org.samlsample;

import org.apache.directory.fortress.core.*;
import org.apache.directory.fortress.realm.J2eePolicyMgr;
import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.samlsample.control.FtBookmarkablePageLink;
import  org.samlsample.control.SecUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * Base class for samlsample project.
 *
 * @author Shawn McKinney
 * @version $Rev$
 */
public abstract class SamlSampleBasePage extends WebPage
{
    // TODO STEP 8a: enable spring injection of fortress bean here:
    @SpringBean
    private AccessMgr accessMgr;
    @SpringBean
    private J2eePolicyMgr j2eePolicyMgr;

    public SamlSampleBasePage()
    {
        // TODO STEP 8b: uncomment call to enableFortress:
        try
        {
            if(!SecUtils.enableFortress( this, ( HttpServletRequest ) getRequest().getContainerRequest(), j2eePolicyMgr, accessMgr ))
            {
                setResponsePage( new RedirectPage( SecUtils.FORTRESS_SAML_UNAUTHORIZED_URL ) );
            }
        }
        catch (org.apache.directory.fortress.core.SecurityException se)
        {
            String error = "WicketSampleBasePage caught security exception : " + se;
            LOG.warn( error );
            throw new RuntimeException( error );
        }
        // TODO STEP 8c: change to FtBookmarkablePageLink:
        add( new FtBookmarkablePageLink( "page1.link", Page1.class ) );
        add( new FtBookmarkablePageLink( "page2.link", Page2.class ) );
        add( new FtBookmarkablePageLink( "page3.link", Page3.class ) );

        // Add link to logout SAML SP only:
        final Link localLink = new Link( "logoutLocal.link" )
        {
            @Override
            public void onClick()
            {
                HttpServletRequest servletReq = (HttpServletRequest)getRequest().getContainerRequest();
                LOG.info( "route user " + getUserId() + " to SAML local logout" );
                // logout out of SAML SP only:
                setResponsePage( new RedirectPage( SecUtils.FORTRESS_SAML_DEMO_LOCAL_LOGOUT_URL ) );
            }
        };
        add( localLink );

        // Add link to logout both SP and IdP:
        final Link globalLink = new Link( "logout.link" )
        {
            @Override
            public void onClick()
            {
                HttpServletRequest servletReq = (HttpServletRequest)getRequest().getContainerRequest();
                LOG.info( "route user " + getUserId() + " to SAML global logout" );
                // logout out of SAML SP and IdP:
                setResponsePage( new RedirectPage( SecUtils.FORTRESS_SAML_DEMO_LOGOUT_URL ) );
            }
        };
        add( globalLink );
        add( new Label( "footer", "This is free and unencumbered software released into the public domain." ) );
    }

    /**
     * Used by the child pages.
     *
     * @param target for modal panel
     * @param msg to log and display user info
     */
    protected void logIt(AjaxRequestTarget target, String msg)
    {
        info( msg );
        LOG.info( msg );
        target.appendJavaScript(";alert('" + msg + "');");
    }

    protected String getUserId()
    {
        return SecUtils.getUserId( this );
    }

    protected static final Logger LOG = Logger.getLogger( SamlSampleBasePage.class.getName() );
}