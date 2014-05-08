package com.rackspace.rvi.vcd

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Request
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ResponseBuilder
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

/**
 * Created with IntelliJ IDEA.
 * User: Michael Rice
 * Twitter: @errr_
 * Website: http://www.errr-online.com/
 * Github: https://github.com/michaelrice
 * Date: 5/7/2014
 * Time: 8:19 PM
 * Licenses: MIT http://opensource.org/licenses/MIT
 */

class WebResponse {
    public static response(Response.Status status, Object message, MediaType mediaType = null) {
        return response(status.statusCode, message, mediaType)
    }

    public static response(int status, Object message, String location, MediaType mediaType = null) {
        ResponseBuilder builder = Response.ResponseBuilder.newInstance()

        builder.status(status)
        builder.entity(message)
        if (mediaType) {
            builder.type(mediaType)
        }
        if (location) {
            LinkGenerator linkGenerator
            //String url = linkGenerator.serverBaseURL
            builder.location(new URI("http://localhost:8080/sandbox/api" + "/${location}"))
        }

        return builder.build()
    }

    public static Response notImplemented(Object message, MediaType mediaType = null) {
        return response(501, message, mediaType)
    }

    public static Response accepted(Object message, MediaType mediaType = null) {
        return response(Response.Status.ACCEPTED, message, mediaType)
    }

    public static Response badRequest(Object message, MediaType mediaType = null) {
        return response(Response.Status.BAD_REQUEST, message, mediaType)
    }

    public static Response requestTimeout(Object message, MediaType mediaType = null) {
        return response(408, message, mediaType)
    }

    public static Response conflict(Object message, MediaType mediaType = null) {
        return response(Response.Status.CONFLICT, message, mediaType)
    }

    public static Response created(Object message, MediaType mediaType = null) {
        return response(Response.Status.CREATED, message, mediaType)
    }

    public static Response forbidden(Object message, MediaType mediaType = null) {
        return response(Response.Status.FORBIDDEN, message, mediaType)
    }

    public static Response gone(Object message, MediaType mediaType = null) {
        return response(Response.Status.GONE, message, mediaType)
    }

    public static Response internalServerError(Object message, MediaType mediaType = null) {
        return response(Response.Status.INTERNAL_SERVER_ERROR, message, mediaType)
    }

    public static Response movedPermanently(Object message, MediaType mediaType = null) {
        return response(Response.Status.MOVED_PERMANENTLY, message, mediaType)
    }

    public static Response noContent(Object message, MediaType mediaType = null) {
        return response(Response.Status.NO_CONTENT, message, mediaType)
    }

    public static Response notAcceptable(Object message, MediaType mediaType = null) {
        return response(Response.Status.NOT_ACCEPTABLE, message, mediaType)
    }

    public static Response notFound(Object message, MediaType mediaType = null) {
        return response(Response.Status.NOT_FOUND, message, mediaType)
    }

    public static Response notModified(Object message, MediaType mediaType = null) {
        return response(Response.Status.NOT_MODIFIED, message, mediaType)
    }

    public static Response ok(Object message, MediaType mediaType = null) {
        return response(Response.Status.OK, message, mediaType)
    }

    public static Response preconditionFailed(Object message, MediaType mediaType = null) {
        return response(Response.Status.PRECONDITION_FAILED, message, mediaType)
    }

    public static Response seeOther(Object message, MediaType mediaType = null) {
        return response(Response.Status.SEE_OTHER, message, mediaType)
    }

    public static Response serviceUnavailable(Object message, MediaType mediaType = null) {
        return response(Response.Status.SERVICE_UNAVAILABLE, message, mediaType)
    }

    public static Response temporaryRedirect(Object message, MediaType mediaType = null) {
        return response(Response.Status.TEMPORARY_REDIRECT, message, mediaType)
    }

    public static Response unauthorized(Object message, MediaType mediaType = null) {
        return response(Response.Status.UNAUTHORIZED, message, mediaType)
    }

    public static Response unsupportedMediaType(Object message, MediaType mediaType = null) {
        return response(Response.Status.UNSUPPORTED_MEDIA_TYPE, message, mediaType)
    }
}