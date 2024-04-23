package com.communityhub.repository

import com.communityhub.model.Comment
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface CommentFeedRepository : PagingAndSortingRepository<Comment, Long> {
    fun findByPostIdOrderByDateMillisDesc(postId: Long, pageable: Pageable): List<Comment>
}
