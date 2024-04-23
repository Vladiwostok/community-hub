package com.communityhub.service

import com.communityhub.model.Community
import com.communityhub.model.Post
import com.communityhub.repository.CommunityRepository
import com.communityhub.repository.PostFeedRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CommunityService(
    private val communityRepository: CommunityRepository,
    private val postFeedRepository: PostFeedRepository
) {
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

    fun getPostsByCommunity(name: String, pageNumber: Int): Pair<Community, List<Post>> {
        val community = getCommunity(name)
        val page = PageRequest.of(pageNumber, 10)
        val posts = postFeedRepository.findByCommunityIdOrderByDateMillisDesc(community.id!!, page)
        return community to posts
    }
}