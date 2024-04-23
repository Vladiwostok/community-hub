package com.communityhub.controller

import com.communityhub.dto.CHubUserDto
import com.communityhub.service.CHubUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LogInController(private val cHubUserService: CHubUserService) {
    @GetMapping
    @ResponseBody
    fun login(@RequestParam name: String, @RequestParam password: String): ResponseEntity<CHubUserDto> {
        val user = cHubUserService.getUser(name)
        if (user.password == password) {
            // return jwt token
            return ResponseEntity.ok(CHubUserDto(user.name, user.password))
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}