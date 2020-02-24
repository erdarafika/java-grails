package com.anwarmuhamat

import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    def authenticationService

    def list() {
        return User.findAll()
    }

    def single(def params) {
        def user = User.findById(params?.id)
        System.out.println(user.password)
        return  user
    }

    def save(def request) {
        def userJson = request.JSON
        def userInstance = new User(userJson)
        String pass = userInstance.password
        userInstance.password = authenticationService.hash(pass)
        userInstance = userInstance.save()

        return userInstance
    }

    def update(def params, def request) {
        def userJson = request.JSON
        def userInstance = User.get(params?.id)
        userInstance.properties = userJson

        userInstance = userInstance.merge()

        return userInstance
    }
}
