package com.test.sandbox.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.apache.commons.logging.LogFactory

class IntensiveAuthenticationProvider implements AuthenticationProvider {

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

            //
            UserAuth userAuth = new UserAuth()
            userAuth.login(untrusted.principal, untrusted.credentials)

            // Check for this to be null. If its null the intensive login failed
            if(!userAuth.isValid()) {
                log.debug("Login for user ${untrusted.principal} failed.")
                throw new BadCredentialsException('Log in failed. It should fail')
            }
            //List authorities = [new SimpleGrantedAuthority('ROLE_USER')]

            // Conditionally add the admin authority
            if (this.isAdmin(untrusted.principal,userAuth)) {
                authorities.add(new SimpleGrantedAuthority('ROLE_ADMIN'))
                log.debug("Found ${untrusted.principal} in the Admin group")
            }

            if(!this.isEngineer(untrusted.principal, userAuth)) {
                authorities.remove(0)
                log.debug("${untrusted.principal} was not found in the Engineer group and was removed from ROLE_USER and added to ROLE_READONLY")
                //authorities.add(new SimpleGrantedAuthority('ROLE_READONLY'))
            }

            // Create a new trusted authentication with the user role
            UsernamePasswordAuthenticationToken trusted = new UsernamePasswordAuthenticationToken(untrusted.principal, untrusted.credentials, authorities)


            // Log the success
            log.info("Login successful --- " + trusted.principal)

            // Return the trusted authentication
            return trusted
        }
        catch (Exception e) {

            // Log the exception
            log.info("Login failed --- " + untrusted.principal + ": " + e.getMessage())

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

    /**
     * An admin will be a user whose name is in an Admin domain
     *
     * @param name
     * @return
     */
    protected boolean isAdmin(String name, UserAuth userAuth) {
        if(userAuth.isMemberOf(userAuth.DEVOPS) || userAuth.isMemberOf(userAuth.ARCHITECT) ||
            userAuth.isMemberOf(userAuth.VQCADMIN)) {
            return true
        }
        return false
    }

    protected boolean isEngineer(String name, UserAuth userAuth) {
        if(userAuth.isMemberOf(userAuth.ENGINEER)) {
            return true
        }
        return false
    }
}