package com.anwarmuhamat

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import org.mindrot.jbcrypt.BCrypt
import grails.gorm.transactions.Transactional

import java.sql.Timestamp

class Response {
    Integer code
    Integer id
    String token
    String message
}

@Transactional
class AuthenticationService {

    String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }

    boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash)
    }

    def issueToken (request) {
        Long duration = ((60 * 60) + 59) * 1000
        Timestamp timestamp = new Timestamp(System.currentTimeMillis())
        Timestamp exp = new Timestamp(timestamp.getTime()+duration)
        def reqBody = request.JSON
        def userInstance = new User(reqBody)
        def user = User.findWhere(username: userInstance.username)
        if (user == null) {
            return new Response(code: 400, message: 'Invalid credentials.')
        }
        if (!verifyHash(userInstance.password, user.password)){
            return new Response(code: 400, message: 'Invalid credentials.')
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256("Kz5BhK8oKohCnj2UFewHWPPXw1bwW4HMm8tjiD259vS8iEGeDRC9")
            String token = JWT.create()
                    .withIssuer("anwarmuhamat")
                    .withIssuedAt(timestamp)
                    .withExpiresAt(exp)
                    .withClaim("id", user.id)
                    .withArrayClaim("roles", 'user', 'admin')
                    .sign(algorithm)
            return new Response(code: 200, token: token)
        } catch (JWTCreationException exception){
            return new Response(code: 500, message: exception.message)
        }
    }

    def tokenVerifier(request) {
        String headerAuth = request.getHeader('Authorization')
        if (!headerAuth) {
            return new Response(code: 400, message: 'Authorization header is required.')
        } else {
            try {
                Algorithm algorithm = Algorithm.HMAC256("Kz5BhK8oKohCnj2UFewHWPPXw1bwW4HMm8tjiD259vS8iEGeDRC9")
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("anwarmuhamat")
                        .build()
                verifier.verify(headerAuth)
                try {
                    DecodedJWT jwtPayload = JWT.decode(headerAuth)
                    Claim claim = jwtPayload.getClaim('id')
                    return new Response(code: 200, id: claim.asInt())
                } catch (JWTDecodeException exception){
                    return new Response(code: 400, message: exception.message)
                }
            } catch (JWTVerificationException exception){
                return new Response(code: 400, message: exception.message)
            }
        }
    }

}
