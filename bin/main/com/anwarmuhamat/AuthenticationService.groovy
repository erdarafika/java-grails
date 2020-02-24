package com.anwarmuhamat

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.Key

class AuthenticationService {

    def token() {
        SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8))
        String jws = Jwts.builder()
        .claim("id", 1234)
        .claim("active", true)
        .claim("roles", ['admin', 'user'])
        .signWith(key).compact()

        return new User(name: 'Erda Rafika', username: 'erdarafika', password: jws)
    }
}
