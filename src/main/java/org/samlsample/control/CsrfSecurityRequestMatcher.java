package org.samlsample.control;

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

    @Override
    public boolean matches(HttpServletRequest request)
    {
        if (allowedMethods.matcher(request.getMethod()).matches())
        {
            return false;
        }

        return !unprotectedMatcher.matches(request);
    }
}
