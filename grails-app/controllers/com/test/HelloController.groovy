package com.test

import grails.plugin.springsecurity.annotation.Secured


class HelloController {

    @Secured("ROLE_USER")
    def index() {
        render "hello world"
    }

    @Secured("ROLE_ADMIN")
    def admin() {
        render "hello admins only"
    }
}