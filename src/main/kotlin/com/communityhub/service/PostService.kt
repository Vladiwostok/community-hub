package com.communityhub.service

import com.communityhub.dto.PostDto
import com.communityhub.model.Comment
import com.communityhub.model.Post
import com.communityhub.repository.CommentFeedRepository
import com.communityhub.repository.CommentRepository
import com.communityhub.repository.PostRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val communityService: CommunityService,
    private val chubUserService: CHubUserService,
    private val commentFeedRepository: CommentFeedRepository
) {
    fun createPost(postDto: PostDto): Long {
        val community = communityService.getCommunity(postDto.communityName)
        val user = chubUserService.getUser(postDto.author!!)
        val post = Post(null, community, user, postDto.title, postDto.content, System.currentTimeMillis())
        val newPost = postRepository.save(post)
        return newPost.id!!
    }

    fun createComment(id: Long, comment: String, name: String) {
        val post = getPost(id)
        val user = chubUserService.getUser(name)
        val comm = Comment(null, post, user, comment, System.currentTimeMillis())
        commentRepository.save(comm)
    }

    fun getPost(id: Long): Post {
        return postRepository.findById(id).get()
    }

    fun getPostWithComments(id: Long, commentPage: Int): Pair<Post, List<Comment>> {
        val post = getPost(id)
        val page = PageRequest.of(commentPage, 10)
        val comments = commentFeedRepository.findByPostIdOrderByDateMillisDesc(id, page)
        return post to comments
    }

    fun deletePost(id: Long) {
        val post = getPost(id)
        postRepository.delete(post)
    }
}