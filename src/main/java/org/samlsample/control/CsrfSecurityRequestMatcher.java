package org.samlsample.control;

import org.samlsample.Page1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@EnableWebSecurity
public class CsrfSecurityRequestMatcher implements RequestMatcher
{
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("^/saml/SSO(.*)", null);
    private static final Logger LOG = LoggerFactory.getLogger( CsrfSecurityRequestMatcher.class.getName() );

    @Override
    public boolean matches(HttpServletRequest request)
    {
        LOG.info ( "CsrfSecurityRequestMatcher.matches");
        if (allowedMethods.matcher(request.getMethod()).matches())
        {
            LOG.info ( "CsrfSecurityRequestMatcher.matches: false");
            return false;
        }
        else
        {
            LOG.info ( "CsrfSecurityRequestMatcher.matches: true");
            LOG.info ( "CsrfSecurityRequestMatcher.matches: value <" + !unprotectedMatcher.matches(request) + ">");
        }

        return !unprotectedMatcher.matches(request);
    }
}
