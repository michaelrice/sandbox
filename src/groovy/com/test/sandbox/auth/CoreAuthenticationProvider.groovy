package com.test.sandbox.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException

import org.apache.commons.logging.LogFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority

class CoreAuthenticationProvider implements AuthenticationProvider {

    def springSecurityService
    def userDetailsService

    private static final log = LogFactory.getLog(this)

    /**
     * Authenticates an untrusted authentication
     *
     * @param Authentication untrusted Untrusted object implementing the authentication interface
     * @return Returns the untrusted authentication on failure, or a new trusted authentication on success
     */
    Authentication authenticate(Authentication untrusted) {

        try {

            // Get a core token string from core
            CoreUserAuth userAuth = new CoreUserAuth()
            userAuth.login(untrusted.principal, untrusted.credentials)
            if(!userAuth.isValid()) {
                throw new BadCredentialsException('CORE Log in failed. Access denied')
            }

            List authorities = [new SimpleGrantedAuthority('ROLE_READONLY')]

            UsernamePasswordAuthenticationToken trusted = new UsernamePasswordAuthenticationToken(untrusted.principal, untrusted.credentials, authorities)

            // Log the success
            log.info("CORE Login success --- " + trusted.principal)

            // Return the trusted authentication
            return trusted
        }
        catch (Exception e) {

            // Log the exception
            log.debug("Login failed --- " + untrusted.principal, e)

            // Get user details
            throw new BadCredentialsException('Log in failed. It should fail')
        }

        return untrusted
    }

    /**
     * Required For the AuthenticationProvider interface
     *
     * @param authentication Class of the authentication object implementing the authentication interface
     * @return boolean True for authentication = CoreToken, false otherwise
     */
    boolean supports(Class authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class)
    }

}