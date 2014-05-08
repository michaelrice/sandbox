package com.rackspace.rvi.vcd

class EventStatusService {

    CreateVirtualMachineEvent getEventStatus(int id) {
        return CreateVirtualMachineEvent.findById(id.longValue()) ?: null
    }
}
