package com.rackspace.rvi.vcd

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Response

@Path('/api/events')
@Produces(['application/json'])
class EventsResource {

    EventStatusService eventStatusService

    @GET
    @Path('{id}')
    Response getEventsRepresentation(@PathParam('id') int id) {
        CreateVirtualMachineEvent cvme = eventStatusService.getEventStatus(id)
        if (!cvme) {
            return WebResponse.notFound("no event with id: ${id} found")
        }
        WebResponse.ok(cvme)
    }
}
