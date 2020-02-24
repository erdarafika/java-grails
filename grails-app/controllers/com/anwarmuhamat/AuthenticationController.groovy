package com.anwarmuhamat

import grails.rest.*

class AuthenticationController extends RestfulController {
    def authenticationService
    // static responseFormats = ['json', 'xml']
    AuthenticationController() {
        super(User)
    }

    def signIn() {
        respond authenticationService.issueToken(request)
    }

}
