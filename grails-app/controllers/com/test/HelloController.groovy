package com.test

import grails.plugin.springsecurity.annotation.Secured


class HelloController {

    @Secured(["ROLE_USER","ROLE_ADMIN","ROLE_READONLY"])
    def index() {}

    @Secured("ROLE_ADMIN")
    def admin() {
        render "hello admins only"
    }

    @Secured(["ROLE_READONLY","ROLE_ADMIN","ROLE_USER"])
    def readonly() {
        render "hello readonly person"
    }

}