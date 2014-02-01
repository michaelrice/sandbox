import grails.plugin.springsecurity.SpringSecurityUtils

// Place your Spring DSL code here
beans = {

    intensiveAuthenticationProvider(com.test.sandbox.auth.IntensiveAuthenticationProvider) { }
    coreAuthenticationProvider(com.test.sandbox.auth.CoreAuthenticationProvider) { }

    activeDirectoryURL(org.springframework.jndi.JndiObjectFactoryBean) {
        jndiName = 'java:comp/env/activeDirectoryURL'
        defaultObject = 'ldaps://332535-intdc10.intensive.int'
    }

}
