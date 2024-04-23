package com.communityhub.controller

import com.communityhub.auth.decodeToken
import com.communityhub.dto.CommentDto
import com.communityhub.dto.PostDto
import com.communityhub.dto.PostWithCommentsDto
import com.communityhub.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/post")
class PostController(private val postService: PostService) {
    @PostMapping("/create")
    fun createPost(
        @RequestHeader("Authorization") authorization: String?,
        @RequestBody postDto: PostDto
    ): ResponseEntity<Long> {
        val token = authorization?.substringAfter("Bearer ") ?: return ResponseEntity.status(403).build()
        val userInfo = decodeToken(token)
        if (userInfo.isExpired())
            return ResponseEntity.status(403).build()

        postDto.name = userInfo.name

        return try {
            val id = postService.createPost(postDto)
            ResponseEntity.ok(id)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/{id}/comment")
    fun createComment(
        @PathVariable id: Long,
        @RequestHeader("Authorization") authorization: String?,
        @RequestBody comment: CommentDto
    ): ResponseEntity<Unit> {
        val token = authorization?.substringAfter("Bearer ") ?: return ResponseEntity.status(403).build()
        val userInfo = decodeToken(token)
        if (userInfo.isExpired())
            return ResponseEntity.status(403).build()

        return try {
            postService.createComment(id, comment.comment, userInfo.name)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(
        @PathVariable id: Long,
        @RequestHeader("Authorization") authorization: String?
    ): ResponseEntity<Unit> {
        val token = authorization?.substringAfter("Bearer ") ?: return ResponseEntity.status(403).build()
        val userInfo = decodeToken(token)

        val post = try {
            postService.getPost(id)
        } catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }

        if (!userInfo.isAdminAuthorized() && (post.chubUser.name != userInfo.name || userInfo.isExpired())) {
            return ResponseEntity.status(403).build()
        }

        return try {
            postService.deletePost(id)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long, @RequestParam("p") commentPage: Int?): ResponseEntity<PostWithCommentsDto> {
        val page = commentPage ?: 0

        return try {
            val post = postService.getPostWithComments(id, page)
            val postDto = PostDto(
                post.first.community.name,
                post.first.chubUser.name,
                post.first.title,
                post.first.content
            )

            val comments = post.second.map {
                CommentDto(it.content, it.chubUser.name)
            }

            ResponseEntity.ok(PostWithCommentsDto(postDto, comments))
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}