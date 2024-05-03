package com.communityhub.controller

import com.communityhub.auth.decodeToken
import com.communityhub.dto.CommunityDto
import com.communityhub.dto.CommunityWithPostsDto
import com.communityhub.dto.PostDto
import com.communityhub.model.Community
import com.communityhub.service.CommunityService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
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
    @SecurityRequirement(name = "JWT Access Token")
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
    @SecurityRequirement(name = "JWT Access Token")
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
    @SecurityRequirement(name = "JWT Access Token")
    fun deleteCommunity(
        @RequestHeader("Authorization") authorization: String?,
        @PathVariable name: String
    ): ResponseEntity<Unit> {
        if (!isAdmin(authorization))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()

        communityService.deleteCommunity(name)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{name}")
    fun getPost(
        @PathVariable name: String,
        @RequestParam("p") commentPage: Int?
    ): ResponseEntity<CommunityWithPostsDto> {
        val page = commentPage ?: 0
        return try {
            val community = communityService.getPostsByCommunity(name, page)
            val communityDto = CommunityDto(community.first.name, community.first.description)
            val posts = community.second.map { PostDto(community.first.name, it.chubUser.name, it.title, it.content) }
            val communityWithPosts = CommunityWithPostsDto(communityDto, posts)
            ResponseEntity.ok(communityWithPosts)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    private fun isAdmin(jwt: String?): Boolean {
        val token = jwt?.substringAfter("Bearer ") ?: return false
        val userInfo = decodeToken(token)
        return userInfo.isAdminAuthorized()
    }
}