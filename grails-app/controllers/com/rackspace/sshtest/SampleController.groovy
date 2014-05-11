package com.rackspace.sshtest

class SampleController {

    ConnectionService connectionService

    def index() {

        //render connectionService.getOutput()
        render connectionService.scpFile("rvi-jeeves.conf", '/tmp')
    }
}
