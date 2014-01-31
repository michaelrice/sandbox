import com.test.sandbox.auth.IntensiveToken
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import grails.plugin.springsecurity.web.authentication.RequestHolderAuthenticationFilter
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

class IntensiveLoginFilter extends RequestHolderAuthenticationFilter {

    @Override
    public void afterPropertiesSet() {
        super.setFilterProcessesUrl('/j_spring_security_check')
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals('POST')) {
            throw new AuthenticationServiceException('Authentication method not supported: ' + request.getMethod())
        }

        IntensiveToken token = new IntensiveToken(request.getParameter('j_username'), request.getParameter('j_password'))
        return this.getAuthenticationManager().authenticate(token)
    }
}