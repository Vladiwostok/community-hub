package com.communityhub.controller

import com.communityhub.dto.PostDto
import com.communityhub.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/main")
class MainFeedController(private val postService: PostService) {
    @GetMapping
    fun getMainFeed(@RequestParam("p") commentPage: Int?): List<PostDto> {
        val page = commentPage ?: 0
        return postService.getMainFeedPosts(page)
            .map { PostDto(it.id, it.community.name, it.chubUser.name, it.title, it.content) }
    }
}