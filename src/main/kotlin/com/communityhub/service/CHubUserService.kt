package com.communityhub.service

import com.communityhub.auth.createToken
import com.communityhub.model.CHubUser
import com.communityhub.repository.CHubUserRepository
import org.springframework.stereotype.Service

@Service
class CHubUserService (private val cHubUserRepository: CHubUserRepository) {
    fun saveUser(cHubUser: CHubUser) {
        cHubUserRepository.save(cHubUser)
    }

    fun getUser(name: String): CHubUser {
        return cHubUserRepository.findByName(name)
    }

    fun updateUserJwtToken(name: String): String {
        val user = getUser(name)
        val token = createToken(name, user.isAdmin ?: false)
        user.jwtToken = token
        cHubUserRepository.save(user)
        return token
    }

    fun deleteUserJwtToken(name: String) {
        val user = getUser(name)
        user.jwtToken = null
        cHubUserRepository.save(user)
    }
}