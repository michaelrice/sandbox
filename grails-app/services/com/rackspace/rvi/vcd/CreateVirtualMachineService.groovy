package com.rackspace.rvi.vcd

import org.apache.log4j.Logger

class CreateVirtualMachineService {

    Logger log = Logger.getLogger(CreateVirtualMachineService)

    void createVirtualMachine(String vmName, CreateVirtualMachineEvent event) {
        log.debug("Creating virtual machine")
        VirtualMachine virtualMachine = new VirtualMachine()
        virtualMachine.name = vmName
        log.debug("waiting 15 seconds before we call it done.")
        Thread.currentThread().sleep(15000)
        virtualMachine.save(flush: true)
        event.percentComplete = 100
        event.completed = true
        event.save(flush: true)
        log.debug("all done making virtual machine")
    }

    CreateVirtualMachineEvent createVirtualMachineCreationEvent(String name) {
        log.debug("Making creation event to track ${name}")
        CreateVirtualMachineEvent cvme = new CreateVirtualMachineEvent()
        cvme.name = name
        cvme.save(flush: true)
        log.debug("Created creation event.")
        return cvme
    }

    CreateVirtualMachineEvent getVirtualMachineCreationEvent(int id) {
        return CreateVirtualMachineEvent.findById(id.longValue())
    }

    void bumpProgress(CreateVirtualMachineEvent event, int stat) {
        event.percentComplete = stat
        event.save(flush: true)
    }
}
