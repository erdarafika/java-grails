package com.anwarmuhamat

import grails.converters.JSON
import grails.rest.*

class UserController extends RestfulController {
    def userService
    def authenticationService
    // static allowedMethods = [index:'GET']

    UserController() {
        super(User)
    }

    @Override
    def index() {
        def isValidToken = authenticationService.tokenVerifier(request)
        if (isValidToken.code != 200) {
            respond isValidToken
        } else {
            response.status = 200
            render userService.list(isValidToken.id) as JSON
        }
    }

    @Override
    def show() {
        respond userService.single(params)
    }

    @Override
    def save() {
        respond userService.save(request)
    }

    @Override
    def update() {
        def user = userService.update(params,request)
        respond user
    }
}
