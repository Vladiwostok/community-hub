package com.communityhub.controller

import com.communityhub.service.CHubUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/logout")
class LogOutController(private val cHubUserService: CHubUserService) {
    @GetMapping
    fun logout(@RequestParam name: String) {
        cHubUserService.deleteUserJwtToken(name)
    }
}