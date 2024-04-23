package com.communityhub.controller

import com.communityhub.dto.CHubUserDto
import com.communityhub.service.CHubUserService
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LogInController(private val cHubUserService: CHubUserService) {
    @GetMapping
    @ResponseBody
    fun login(@RequestParam name: String, @RequestParam password: String): ResponseEntity<String> {
        return try {
            val token = cHubUserService.updateUserJwtToken(name)
            ResponseEntity.ok(token)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}