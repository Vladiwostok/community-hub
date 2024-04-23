package com.communityhub.controller

import com.communityhub.auth.decodeToken
import com.communityhub.service.CHubUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/logout")
class LogOutController(private val cHubUserService: CHubUserService) {
    @GetMapping
    // get Authorization: Bearer token
    fun logout(@RequestHeader("Authorization") authorization: String?): ResponseEntity<Unit> {
        val token = authorization?.substringAfter("Bearer ")
            ?: return ResponseEntity.status(HttpStatus.FORBIDDEN).build()

        val name = decodeToken(token).name
        cHubUserService.deleteUserJwtToken(name)
        return ResponseEntity.ok().build()
    }
}