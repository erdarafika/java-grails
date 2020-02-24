package com.anwarmuhamat

import grails.rest.*

class UserController extends RestfulController {
    def userService
//    static responseFormats = ['json', 'xml']
//    static allowedMethods = [login:'POST']

    UserController() {
        super(User)
    }

    @Override
    def index() {
        respond userService.list()
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
