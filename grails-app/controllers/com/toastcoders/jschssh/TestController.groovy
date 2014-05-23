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
                localFile = "/home/errr/bashrc"
                remoteFile = "/tmp/hello_world.txt"
                strictHostKeyChecking = "yes"
                preserveTimeStamps = false
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

        try {
            new ScpFileFrom().execute() {
                host = "10.12.254.10"
                username = "root"
                password = "password"
                localFile = "/home/errr/my_cool_new_file.txt"
                remoteFile = "/tmp/hello_world.txt"
            }
        }
        catch (JSchException je) {
            log.trace("Failed to copy file from remote host to local host.")
        }
    }
}
