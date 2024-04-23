package com.communityhub.controller

import com.communityhub.dto.CHubUserDto
import com.communityhub.model.CHubUser
import com.communityhub.service.CHubUserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/signup")
class SignUpController (private val cHubUserService: CHubUserService) {
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userDto: CHubUserDto) {
        val cHubUser = CHubUser(null, userDto.name, userDto.password)
        cHubUserService.saveUser(cHubUser)
    }
}