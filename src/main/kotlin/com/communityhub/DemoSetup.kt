package com.communityhub

import com.communityhub.model.CHubUser
import com.communityhub.service.CHubUserService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DemoSetup (private val cHubUserService: CHubUserService) {
    @PostConstruct
    fun setup() {
        setupAdminUser()
        println("Setup demo app")
    }

    private fun setupAdminUser() {
        val user = CHubUser(null, "admin", "admin", true)
        cHubUserService.saveUser(user)
    }
}
