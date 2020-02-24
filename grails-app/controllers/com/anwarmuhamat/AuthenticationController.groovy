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
        def isValidToken = authenticationService.tokenVerifier(request)
        if (isValidToken.code != 200) {
            respond new Response(code: 401, message: 'Unauthorized.')
        }
        respond isValidToken
    }

    @Override
    def save() {
        respond authenticationService.issueToken(request)
    }

}
