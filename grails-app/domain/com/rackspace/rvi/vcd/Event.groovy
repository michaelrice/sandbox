package com.rackspace.rvi.vcd

/**
 * Created with IntelliJ IDEA.
 * User: Michael Rice
 * Twitter: @errr_
 * Website: http://www.errr-online.com/
 * Github: https://github.com/michaelrice
 * Date: 5/7/2014
 * Time: 7:30 PM
 * Licenses: MIT http://opensource.org/licenses/MIT
 */
class Event {

    String name
    int percentComplete
    boolean completed = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
}
