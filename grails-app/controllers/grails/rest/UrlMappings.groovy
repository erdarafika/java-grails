package grails.rest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        group "/v1", {
            get "/users/register"(controller: 'user', action: 'save')
            post "/users/login"(controller: 'authentication', action: 'signIn')
            get "/users/me"(controller: 'user', action: 'index')
            put "/users/me"(controller: 'user', action: 'update')
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
