import grails.plugin.springsecurity.SpringSecurityUtils

// Place your Spring DSL code here
beans = {

    intensiveAuthenticationProvider(com.test.sandbox.auth.IntensiveAuthenticationProvider) { }
    authenticationProcessingFilter(IntensiveLoginFilter) {
        def conf = SpringSecurityUtils.securityConfig

        authenticationManager = ref('authenticationManager')
        sessionAuthenticationStrategy = ref('sessionAuthenticationStrategy')
        authenticationSuccessHandler = ref('authenticationSuccessHandler')
        authenticationFailureHandler = ref('authenticationFailureHandler')
        rememberMeServices = ref('rememberMeServices')
        authenticationDetailsSource = ref('authenticationDetailsSource')

        filterProcessesUrl = conf.apf.filterProcessesUrl
        usernameParameter = conf.apf.usernameParameter
        passwordParameter = conf.apf.passwordParameter
        continueChainBeforeSuccessfulAuthentication = conf.apf.continueChainBeforeSuccessfulAuthentication
        allowSessionCreation = conf.apf.allowSessionCreation
        postOnly = conf.apf.postOnly
    }

    activeDirectoryURL(org.springframework.jndi.JndiObjectFactoryBean) {
        jndiName = 'java:comp/env/activeDirectoryURL'
        defaultObject = 'ldaps://332535-intdc10.intensive.int'
    }

}
