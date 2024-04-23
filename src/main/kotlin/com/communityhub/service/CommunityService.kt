package com.communityhub.service

import com.communityhub.model.Community
import com.communityhub.repository.CommunityRepository
import org.springframework.stereotype.Service

@Service
class CommunityService (private val communityRepository: CommunityRepository) {
    fun createCommunity(community: Community) {
        communityRepository.save(community)
    }

    fun getCommunity(name: String): Community {
        return communityRepository.findByName(name)
    }

    fun getAllCommunities(): List<Community> {
        return communityRepository.findAllByOrderByNameAsc()
    }

    fun updateCommunity(name: String, description: String) {
        val community = getCommunity(name)
        community.description = description
        communityRepository.save(community)
    }

    fun deleteCommunity(name: String) {
        val community = getCommunity(name)
        communityRepository.delete(community)
    }
}