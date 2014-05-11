package com.rackspace.sshtest

import grails.plugin.remotessh.AuthType
import grails.plugin.remotessh.RemoteSCP
import grails.plugin.remotessh.RemoteSSH
import grails.plugin.remotessh.TerminalType

class ConnectionService {

    def grailsApplication
    def sshConfig

    public String getOutput() {
        return new RemoteSSH().Result {
            host = "10.12.254.10"
            user = 'root'
            userpass = 'password'
            port = 22
            usercommand = "vmkping -c 1 -X 10.12.254.1"
            authType = AuthType.KEYBOARD_INTERACTIVE
            sshConfigObject = sshConfig
            shell = "/bin/sh"
            terminalType = TerminalType.VT220
        }
    }

    public String scpFile(String fileName, String destination) {
        return new RemoteSCP().Result {
            host = "10.12.254.10"
            user = 'root'
            userpass = 'password'
            port = 22
            authType = AuthType.KEYBOARD_INTERACTIVE
            sshConfigObject = sshConfig
            file = fileName
            remotedir = destination
        }
    }
}
