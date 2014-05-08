package com.rackspace.rvi.vcd

import com.budjb.rabbitmq.MessageContext
import org.springframework.transaction.annotation.Transactional

class TestConsumer {
    /**
     * Consumer configuration.
     */
    static rabbitConfig = [
        queue : "helloWorld"
    ]

    /**
     * Handle an incoming RabbitMQ message.
     *
     * @param body    The converted body of the incoming message.
     * @param context Properties of the incoming message.
     * @return
     */
    @Transactional
    def handleMessage(List body, MessageContext context) {
        // TODO: Handle Message.

        Maker maker = new Maker()
        println VirtualMachine.findAll().size()
    }
}
