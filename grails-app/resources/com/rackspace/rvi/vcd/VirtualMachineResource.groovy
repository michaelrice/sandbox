package com.rackspace.rvi.vcd

import com.budjb.rabbitmq.RabbitMessageBuilder
import org.apache.log4j.Logger

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Response

@Path('/api/virtualMachine')
@Consumes(['application/json'])
@Produces(['application/json'])
class VirtualMachineResource {

    CreateVirtualMachineService createVirtualMachineService
    Logger log = Logger.getLogger(VirtualMachineResource)

    @POST
    Response createVirtualMachineRepresentation(String name) {
        CreateVirtualMachineEvent event = createVirtualMachineService.createVirtualMachineCreationEvent(name)
        if (!event) {
            return WebResponse.internalServerError("unable to create event")
        }
        new RabbitMessageBuilder().send() {
            exchange = 'sandbox'
            body = name
            routingKey = 'createVirtualMachine'
            headers = ["eventId" : event.id]
        }
        log.debug("Updating % complete to 66")
        createVirtualMachineService.bumpProgress(event,33)
        return WebResponse.response(201,"","events/${event.id}")
    }
}