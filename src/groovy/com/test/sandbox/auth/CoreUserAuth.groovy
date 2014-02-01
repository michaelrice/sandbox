package com.test.sandbox.auth

import com.budjb.requestbuilder.RequestBuilder
import grails.util.Holders
import org.apache.log4j.Logger


class CoreUserAuth {

    private final Logger log = Logger.getLogger(CoreUserAuth.class)
    def grailsApplication = Holders.grailsApplication
    String coreUrl = grailsApplication.config.core.location + 'ctkapi/login/'
    boolean valid = false
    /**
     * Attempts to get an auth token from core
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public String login(String username, String password) {
        try{
            log.trace("Starting CORE auth for ${username}")
            def token = new RequestBuilder().post() {
                uri = coreUrl + "${username}"
                ignoreInvalidSSL = true
                body = ["password": password]
            }
            // Start the client
            log.debug("Token: ${token.toString()}")
            valid = true
            return token
        }
        catch(Exception e) {
            log.debug("CORE Auth failed for ${username}", e)
            valid = false
        }
    }
}