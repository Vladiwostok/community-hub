package com.communityhub.service

import com.communityhub.model.CHubUser
import com.communityhub.repository.CHubUserRepository
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class CHubUserService (val cHubUserRepository: CHubUserRepository) {
    fun saveUser(cHubUser: CHubUser) {
        cHubUserRepository.save(cHubUser)
    }

    fun getUser(name: String): CHubUser {
        return cHubUserRepository.findByName(name)
    }

    fun updateUserJwtToken(name: String): String {
        val user = getUser(name)

        val key = Jwts.SIG.HS256.key().build()
        val jwtToken = Jwts.builder()
            .subject(user.name)
            .claim("isAdmin", user.isAdmin)
            .signWith(key)
            .compact()

        user.jwtToken = jwtToken
        cHubUserRepository.save(user)

        return jwtToken
    }

    fun deleteUserJwtToken(name: String) {
        val user = getUser(name)
        user.jwtToken = null
        cHubUserRepository.save(user)
    }
}