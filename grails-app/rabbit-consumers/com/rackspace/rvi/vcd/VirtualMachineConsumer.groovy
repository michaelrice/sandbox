package com.rackspace.rvi.vcd

import com.budjb.rabbitmq.MessageContext
import org.apache.log4j.Logger

class VirtualMachineConsumer {
    /**
     * Consumer configuration.
     */
    static rabbitConfig = [
        queue : 'sandbox.createVirtualMachine'
    ]

    Logger log = Logger.getLogger(VirtualMachineConsumer)

    /**
     *
     */
    CreateVirtualMachineService createVirtualMachineService

    /**
     * Handle an incoming RabbitMQ message.
     *
     * @param body    The converted body of the incoming message.
     * @param context Properties of the incoming message.
     * @return
     */
    void handleMessage(String name, MessageContext context) {
        log.debug("New message arrived to create virtual machine.")
        int eventId = context.properties.headers["eventId"] as int
        log.debug('Located event to update with progress: ' + eventId)
        CreateVirtualMachineEvent event = createVirtualMachineService.getVirtualMachineCreationEvent(eventId)
        log.debug("Updating % complete to 66")
        createVirtualMachineService.bumpProgress(event, 66)
        createVirtualMachineService.createVirtualMachine(name, event)
        log.debug('Completed creating vm.')
    }
}
