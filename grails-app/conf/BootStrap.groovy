import com.test.sandbox.Role

class BootStrap {

    def init = { servletContext ->
        /*
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(flush: true)
        def userId = 1
        if (!User.findById(userId)) {
            def adminUser = new User(username: 'hero', enabled: true, password: 'secure')
            adminUser.save(flush: true)
            UserRole.create adminUser, adminRole, true
            def userUser = new User(username: 'user', enabled: true, password: 'secure2')
            userUser.save(flush: true)
            UserRole.create( userUser,userRole, true)
        }*/

        def userRole
        if (!(userRole = Role.findByAuthority('ROLE_USER'))) {
            userRole = new Role(authority: 'ROLE_USER').save(flush: true)
        }

        def adminRole
        if (!(adminRole = Role.findByAuthority('ROLE_ADMIN'))) {
            adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true)
        }

        def readonlyRole
        if (!(readonlyRole = Role.findByAuthority('ROLE_READONLY'))) {
            readonlyRole = new Role(authority: 'ROLE_READONLY').save(flush: true)
        }
    }

    def destroy = {

    }

}