package grails.rest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        get "/v1/users/register"(controller: 'user', action: 'save')
        post "/v1/users/login"(controller: 'authentication', action: 'signIn')
        get "/v1/users/me"(controller: 'user', action: 'index')
        put "/v1/users/me"(controller: 'user', action: 'update')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
