package com.anwarmuhamat

import grails.rest.*

class UserController extends RestfulController {
    def userService
    def authenticationService
//    static responseFormats = ['json', 'xml']
//    static allowedMethods = [login:'POST']

    UserController() {
        super(User)
    }

    @Override
    def index() {
        def isValidToken = authenticationService.tokenVerifier(request)
        if (isValidToken.code != 200) {
            respond isValidToken
        } else {
            respond userService.list(isValidToken.id)
        }
    }

    @Override
    def show() {
        respond userService.single(params)
    }

    @Override
    def save() {
        def user = userService.save(request)
        respond user
    }

    @Override
    def update() {
        def user = userService.update(params,request)
        respond user
    }
}
