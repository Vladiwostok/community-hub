package com.communityhub.controller

import com.communityhub.auth.decodeToken
import com.communityhub.dto.CommunityDto
import com.communityhub.model.Community
import com.communityhub.service.CommunityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/community")
class CommunityController(private val communityService: CommunityService) {
    @GetMapping("/all")
    fun getAllCommunities(): List<CommunityDto> {
        return communityService.getAllCommunities().map { CommunityDto(it.name, it.description) }
    }

    @PostMapping("/create")
    fun createCommunity(
        @RequestHeader("Authorization") authorization: String?,
        @RequestBody communityDto: CommunityDto
    ): ResponseEntity<Unit> {
        if (!isAdmin(authorization))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()

        val community = Community(null, communityDto.name, communityDto.description)
        communityService.createCommunity(community)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/update/{name}")
    fun updateCommunity(
        @RequestHeader("Authorization") authorization: String?,
        @PathVariable name: String,
        @RequestParam description: String
    ): ResponseEntity<Unit> {
        if (!isAdmin(authorization))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()

        communityService.updateCommunity(name, description)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/delete/{name}")
    fun deleteCommunity(
        @RequestHeader("Authorization") authorization: String?,
        @PathVariable name: String
    ): ResponseEntity<Unit> {
        if (!isAdmin(authorization))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()

        communityService.deleteCommunity(name)
        return ResponseEntity.ok().build()
    }

    private fun isAdmin(jwt: String?): Boolean {
        val token = jwt?.substringAfter("Bearer ") ?: return false
        val userInfo = decodeToken(token)
        return userInfo.isAdminAuthorized()
    }
}