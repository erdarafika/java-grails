package com.anwarmuhamat

class User {
    Long id
    String name
    String username
    String password

    static constraints = {
        name blank: false
        username unique: true
        password blank: false
    }
}

// grails create-service com.anwarmuhamat.User
// grails create-restful-controller com.anwarmuhamat.User