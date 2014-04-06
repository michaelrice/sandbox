package com.test.sandbox

import com.fasterxml.jackson.databind.JsonNode

import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.main.JsonSchema
import com.github.fge.jsonschema.main.JsonSchemaFactory
import com.github.fge.jsonschema.report.ProcessingReport
import com.rackspace.rvi.vcd.Maker
import com.rackspace.rvi.vcd.VirtualMachine
import groovy.json.JsonSlurper


class HelloController {

    def index() {
        Maker maker = new Maker()
        def vms = VirtualMachine.findAll()
        render "${vms}"
        return
        /*
        JsonLoader jsonLoader = new JsonLoader()
        JsonNode prodSchema = JsonLoader.fromString('''
{
    "$schema": "http://json-schema.org/draft-04/schema#",
    "type": "object",
    "properties": {
        "coreStatus": {
            "type": "string"
        },
        "hostOSLicenseTypes": {
            "type": "array"
        }
    },
    "patternProperties" : {
        "^platform:[A-Z0-9_]+$" : {
            "$ref" : "#/definitions/deviceConfig"
        },
        "^osName:[A-Z0-9_]+$" : {
            "$ref" : "#/definitions/deviceConfig"
        },
        "^appLicenses:[A-Z0-9_]+$" : {
            "$ref" : "#/definitions/deviceConfig"
        },
        "^osSupport:[A-Z0-9_]+$" : {
            "$ref" : "#/definitions/deviceConfig"
        }
    },
    "additionalProperties": false,
    "definitions" : {
        "deviceConfig": {
            "type" : "object",
            "properties" : {
                "type": { "enum" : [ "DeviceConfig"]},
                "category" : {"enum" : [ "platform", "appLicenses", "osSupport", "osName"]},
                "name" : { "type" : "string"},
                "attributes" : { "type" : "object" }
            },
            "additionalProperties": false
        }
    }
}
''')
        JsonNode data = JsonLoader.fromString("""
{
    "coreStatus": "ONLINE_COMPLETE",
    "hostOSLicenseTypes": ["WINDOWS"],
    "platform:WIN_2008_STD_X64": {
      "type": "DeviceConfig",
      "category": "platform",
      "name": "WIN_2008_STD_X64",
      "attributes": {}
    },
    "osName:WINDOWS_2008_STD_X64": {
      "type": "DeviceConfig",
      "category": "osName",
      "name": "WINDOWS_2008_STD_X64",
      "attributes": {
        "type": "WINDOWS"
      }
    },
    "appLicenses:MSSQL_WEB": {
      "type": "DeviceConfig",
      "category": "appLicenses",
      "name": "MSSQL_WEB",
      "attributes": {
        "provider": "RACKSPACE",
        "supported": "Rackspace Managed"
      }
    },
    "appLicenses:MBU_MSSQL_AGENT": {
      "type": "DeviceConfig",
      "category": "appLicenses",
      "name": "MBU_MSSQL_AGENT",
      "attributes": {
        "provider": "RACKSPACE",
        "supported": "Rackspace Managed"
      }
    },
    "osSupport:RACKSPACE_MANAGED": {
      "type": "DeviceConfig",
      "category": "appLicenses",
      "name": "RACKSPACE_MANAGED",
      "attributes": {}
    }
}
""")
        ProcessingReport report
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault()
        JsonSchema schema = factory.getJsonSchema(prodSchema)
        report = schema.validate(data)
        if(report.success) {
            def payload = new JsonSlurper().parseText(data.toString())
            payload.each {key, value ->
                render "raxdata_${key}  ->  ${value} <br />"
            }
        }
        return
        render "hello world"
        */
    }
}