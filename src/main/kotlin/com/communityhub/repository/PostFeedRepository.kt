package com.communityhub.repository

import com.communityhub.model.Post
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface PostFeedRepository : PagingAndSortingRepository<Post, Long> {
    fun findByCommunityIdOrderByDateMillisDesc(communityId: Long, pageable: Pageable): List<Post>

    fun findAllByOrderByDateMillisDesc(pageable: Pageable): List<Post>
}