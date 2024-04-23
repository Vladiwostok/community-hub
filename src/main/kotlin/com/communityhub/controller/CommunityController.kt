package com.communityhub.controller

import com.communityhub.dto.CommunityDto
import com.communityhub.model.Community
import com.communityhub.service.CommunityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/community")
class CommunityController(private val communityService: CommunityService) {
    @GetMapping("/all")
    fun getAllCommunities(): List<CommunityDto> {
        return communityService.getAllCommunities().map { CommunityDto(it.name, it.description) }
    }

    @PostMapping("/create")
    fun createCommunity(@RequestBody communityDto: CommunityDto) {
        // TODO: Check if is admin
        val community = Community(null, communityDto.name, communityDto.description)
        communityService.createCommunity(community)
    }

    @PutMapping("/update/{name}")
    fun updateCommunity(@PathVariable name: String, @RequestParam description: String) {
        // TODO: Check if is admin
        communityService.updateCommunity(name, description)
    }

    @DeleteMapping("/delete/{name}")
    fun deleteCommunity(@PathVariable name: String) {
        // TODO: Check if is admin
        communityService.deleteCommunity(name)
    }
}