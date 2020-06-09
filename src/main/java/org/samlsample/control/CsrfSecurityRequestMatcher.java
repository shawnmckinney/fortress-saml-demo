package org.samlsample.control;

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
        LOG.debug ( "CsrfSecurityRequestMatcher.matches method: " + request.getMethod() );
        LOG.debug ( "CsrfSecurityRequestMatcher.matches uri: " + request.getRequestURI() );
        LOG.debug ( "CsrfSecurityRequestMatcher.matches context: " + request.getContextPath() );
        if (allowedMethods.matcher(request.getMethod()).matches())
        {
            LOG.debug ( "CsrfSecurityRequestMatcher allowedMethods.matches: false");
            return false;
        }
        else
        {
            LOG.debug ( "CsrfSecurityRequestMatcher allowedMethods.matches: true");
            LOG.debug ( "CsrfSecurityRequestMatcher !unprotectedMatcher.matches: value <" + !unprotectedMatcher.matches(request) + ">");
        }

        return !unprotectedMatcher.matches(request);
    }
}
