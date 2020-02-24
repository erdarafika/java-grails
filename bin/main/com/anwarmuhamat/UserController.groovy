package com.anwarmuhamat

import org.grails.web.servlet.mvc.GrailsWebRequest
import grails.rest.*
import grails.converters.*

class UserController extends RestfulController {
    def userService
    def authenticationService
    static responseFormats = ['json', 'xml']
    UserController() {
        super(User)
    }

    @Override
    def index() {
        // respond userService.list(params,request)
        respond authenticationService.token()
    }

    @Override
    def show() {
        respond userService.single(params,request)
    }

    @Override
    def save() {
        def user = userService.save(params,request)
        respond user
    }

    @Override
    def update() {
        def user = userService.update(params,request)
        respond user
    }
}
