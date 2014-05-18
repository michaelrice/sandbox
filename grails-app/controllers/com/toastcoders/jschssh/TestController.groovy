package com.toastcoders.jschssh

import com.jcraft.jsch.JSchException
import com.toastcoders.jschssh.RunSshCommand
import com.toastcoders.jschssh.ScpFileTo


class TestController {

    def index() {

        try {
            new ScpFileTo().execute() {
                host = "10.12.254.10"
                username = "root"
                password = "password"
                localFile = "C:/users/errr/desktop/hello_world.txt"
                remoteFile = "/tmp/hello_world.txt"
                strictHostKeyChecking = "yes"
                ptimestamp = false
            }
        }
        catch (JSchException e) {
            log.trace("Oh noes!!", e)
        }

        render new RunSshCommand().execute() {
            host = "10.12.254.10"
            username = "root"
            password = "password"
            command = "esxcli --formatter=xml hardware pci list"
            strictHostKeyChecking = "yes"
        }
    }
}
