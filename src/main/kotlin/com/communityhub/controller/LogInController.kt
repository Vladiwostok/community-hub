package com.communityhub.controller

import com.communityhub.service.CHubUserService
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
@SecurityScheme(
    name = "JWT Access Token",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    `in` = SecuritySchemeIn.HEADER
)
class LogInController(private val cHubUserService: CHubUserService) {
    @GetMapping
    @ResponseBody
    fun login(@RequestParam name: String, @RequestParam password: String): ResponseEntity<String> {
        return try {
            val token = cHubUserService.updateUserJwtToken(name)
//            ResponseEntity.ok(token)
            ResponseEntity.ok().header("Authorization", token).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}