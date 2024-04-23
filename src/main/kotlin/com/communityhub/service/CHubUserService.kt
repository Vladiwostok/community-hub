package com.communityhub.service

import com.communityhub.model.CHubUser
import com.communityhub.repository.CHubUserRepository
import org.springframework.stereotype.Service

@Service
class CHubUserService (val cHubUserRepository: CHubUserRepository) {
    fun saveUser(cHubUser: CHubUser) {
        cHubUserRepository.save(cHubUser)
    }

    fun getUser(name: String): CHubUser {
        return cHubUserRepository.findByName(name)
    }
}