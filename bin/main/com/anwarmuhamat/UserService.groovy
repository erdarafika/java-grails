package com.anwarmuhamat

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def list(def params, def request) {
        return User.findAll()
    }

    def single(def params, def request) {
        return User.findById(params?.id)
    }

    def save(def params, def request) {
        def userJson = request.JSON
        def userInstance = new User(userJson)

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
