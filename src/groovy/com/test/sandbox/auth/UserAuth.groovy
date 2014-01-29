package com.test.sandbox.auth

import grails.util.Environment
import grails.util.Holders

import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.directory.Attribute
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import org.apache.log4j.Logger

/**
 * Created with IntelliJ IDEA.
 * User: Michael Rice
 * Twitter: @errr_
 * Website: http://www.errr-online.com/
 * Github: https://github.com/michaelrice
 * Date: 1/28/14
 * Time: 5:11 PM
 * Licenses: MIT http://opensource.org/licenses/MIT
 */
class UserAuth {
    private final Logger log = Logger.getLogger(UserAuth.class)
    /**
     * Group constants.
     */
    public final static ENGINEER = "ROLE_USER"
    public final static ARCHITECT = "ROLE_ADMIN"
    public final static DEVOPS = "ROLE_ADMIN"
    public final static VQCADMIN = "ROLE_ADMIN"
    def activeDirectoryURL = Holders.applicationContext.activeDirectoryURL

    /**
     * User's login.
     */
    private def username

    /**
     * User's password.
     */
    private def password

    /**
     * User's actual name.
     * I'm currently using the CN field to grab this.
     */
    private def name

    /**
     * Whether the query has been sent yet.
     */
    private def queried = false

    /**
     * Whether the AD query was valid or not.
     */
    private def valid = false

    /**
     * Groups the user is a member of.
     */
    private groups = new ArrayList()

    /**
     * Logs in a user
     *
     * @param username User's username.
     * @param password User's password.
     * @return void
     */
    public login(String username, String password) {
        log.debug("Starting new intensive login for: ${username}")
        // Make sure the domain is prepended
        if (username.length() < 11 || !username.substring(0, 10).toLowerCase().equals("intensive\\")) {
            username = "intensive\\" + username
        }

        // Store the credentials
        this.username = username
        this.password = password

        query()
    }

    /**
     * Returns whether the login is valid.
     *
     * @return boolean
     */
    public def isValid()
    {
        if (!queried)
        {
            return false
        }

        return valid
    }

    /**
     * Does the actual query to the LDAP server.
     *
     * @return void
     */
    private def query()
    {
        // Set up the request parameters
        Hashtable env = new Hashtable(7)
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
        env.put(Context.SECURITY_AUTHENTICATION, "simple")
        env.put(Context.PROVIDER_URL, activeDirectoryURL)
        env.put(Context.REFERRAL, "follow")
        env.put(Context.SECURITY_PROTOCOL, "ssl")
        env.put(Context.SECURITY_PRINCIPAL, username)
        env.put(Context.SECURITY_CREDENTIALS, password)

        // Mark the instance as queried.
        queried = true

        // Catch-all
        try {
            // Create the LDAP connection
            DirContext ctx = new InitialDirContext(env)
            log.debug("Intensive login ctx created")
            // Create the searcher
            SearchControls ctls = new SearchControls()
            log.debug("Intensive login ctls created")

            // This is the second phase in the login process where we assign AD permissions.
            // if they login passes the first phase, we move on to the second. All valid
            // names will be of the form intensive user.maybelastname so we need to split
            // this so we can query the user from AD without the unneeded intensive part.
            String[] parts = username.split("\\\\")
            String filter = "samAccountName=" + parts[1]
            log.debug("Intensive login filter: ${filter}")

            // Search strings
            NamingEnumeration answer = ctx.search("ou=Accounts,ou=Virtualization,ou=Rackspace-Infrastructure,dc=intensive,dc=int", filter, ctls)

            // If no answers are given, the login was invalid
            if (!answer.hasMore()) {
                ctx.close()
                return
            }

            // Check all results
            while (answer.hasMore()){
                // Get the current answer row
                SearchResult sr = (SearchResult)(answer.next())

                // Get the membership attribute
                Attribute attr = sr.getAttributes().get("memberOf")

                // Check for group memberships
                if (attr.toString() =~ /RVI-Engineer/) {
                    groups.add(ENGINEER)
                }
                if (attr.toString() =~ /RVI-Architects/) {
                    groups.add(ARCHITECT)
                }
                if (attr.toString() =~ /RVI-DevOps/) {
                    groups.add(DEVOPS)
                }
                if (attr.toString() =~ /RVI-VQC-Admin/) {
                    groups.add(VQCADMIN)
                }

                // Set the user name to nothing
                name = null

                // Get the cn attribute
                attr = sr.getAttributes().get("cn")

                // Attempt to use the cn name
                if (attr.size() > 0) {
                    def str = attr.get()
                    if (str.length() > 0) {
                        name = str
                    }
                }

                // If the name is still empty, just user the login
                if (name == null) {
                    name = username
                }
            }

            // Close the LDAP connection
            ctx.close()

            // Mark the login as valid
            valid = true
        } catch (Exception e) {
            // Do nothing. It failed and the login is invalid
            log.debug("Failed intensive login.", e)
        }
    }

    /**
     * Returns whether the login is a member of the passed group.
     *
     * @param group Group to check membership of.
     * @return boolean
     */
    public def isMemberOf(String group)
    {
        if (!queried) {
            return false
        }

        return (groups.contains(group))
    }

    /**
     * Returns the username associated with this login.
     *
     * @return String
     */
    public def getUsername()
    {
        return username
    }

    /**
     * Returns the user's real name (if available)
     *
     * @return String
     */
    public def getName()
    {
        return name
    }

}
