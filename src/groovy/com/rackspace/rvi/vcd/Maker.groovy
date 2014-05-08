package com.rackspace.rvi.vcd

/**
 * Created with IntelliJ IDEA.
 * User: Michael Rice
 * Twitter: @errr_
 * Website: http://www.errr-online.com/
 * Github: https://github.com/michaelrice
 * Date: 3/11/14
 * Time: 9:28 PM
 * Licenses: MIT http://opensource.org/licenses/MIT
 */
class Maker {

    public Maker() {
        VirtualMachine virtualMachine = new VirtualMachine()
        virtualMachine.name = "My name"
        virtualMachine.save(flush: true)
    }
}
