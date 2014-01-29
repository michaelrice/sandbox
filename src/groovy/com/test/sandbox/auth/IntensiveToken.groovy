package com.test.sandbox.auth


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

/**
 * Created with IntelliJ IDEA.
 * User: Michael Rice
 * Twitter: @errr_
 * Website: http://www.errr-online.com/
 * Github: https://github.com/michaelrice
 * Date: 1/28/14
 * Time: 4:48 PM
 * Licenses: MIT http://opensource.org/licenses/MIT
 */

class IntensiveToken extends UsernamePasswordAuthenticationToken {

    String intensiveAuthToken

    public IntensiveToken(Object principal, Object credentials) {
        super(principal, credentials)
    }

    public IntensiveToken(Object principal, Object credentials, Collection<GrantedAuthority> authorities) {
        super(principal, credentials, authorities)
    }
}