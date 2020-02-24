package com.anwarmuhamat


import grails.rest.*

class AuthenticationController extends RestfulController {
    def authenticationService
    // static responseFormats = ['json', 'xml']
    AuthenticationController() {
        super(User)
    }

    @Override
    def index() {
        respond authenticationService.tokenVerifier(request)
    }

    @Override
    def save() {
        respond authenticationService.issueToken(request)
    }

}
