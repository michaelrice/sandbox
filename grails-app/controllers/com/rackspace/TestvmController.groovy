package com.rackspace

import com.vmware.vim25.*
import com.vmware.vim25.mo.*
import org.apache.log4j.Logger

import java.rmi.RemoteException

class TestvmController {

    Logger log = Logger.getLogger(TestvmController)

    def index() {

        ServiceInstance si
        URL url
        try {
            url = new URL("https://dvc02-1018700.mv.rackspace.com/sdk")
            si = new ServiceInstance(url, "", "", true)
            VirtualMachine vm = si.getSearchIndex().findByUuid(null, "4238d06e-feba-df76-864c-1b7532250b45", true)
            render(vm.name + "<br />")
            render(vm.config.VAppConfig.properties)
            log.debug(vm.name)
            render "<br />done"
            return
        }
        catch (Exception e) {
            log.error('AccountInventory - malformed url - ', e)
            throw new Exception('vcenterstore.test.malformedUrl')
        }
    }
}
