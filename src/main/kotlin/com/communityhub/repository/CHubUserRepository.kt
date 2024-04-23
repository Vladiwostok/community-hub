package com.communityhub.repository

import com.communityhub.model.CHubUser
import org.springframework.data.jpa.repository.JpaRepository

interface CHubUserRepository : JpaRepository<CHubUser, Long> {
    fun findByName(name: String): CHubUser
}