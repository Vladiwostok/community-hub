package com.communityhub.controller

import com.communityhub.dto.CommentDto
import com.communityhub.dto.PostDto
import com.communityhub.model.CHubUser
import com.communityhub.model.Community
import com.communityhub.service.CHubUserService
import com.communityhub.service.CommunityService
import com.communityhub.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/demo")
class DemoController(
    private val cHubUserService: CHubUserService,
    private val communityService: CommunityService,
    private val postService: PostService
) {
    private val demoUser = CHubUser(null, "demo", "demo")

    @GetMapping
    fun demo() {
        createDemoUser()
        createDemoCommunities()
        createDemoPostsAndComments()
    }

    private fun createDemoUser() {
        cHubUserService.saveUser(demoUser)
    }

    private fun createDemoCommunities() {
        val communities = listOf(
            Community(null, "Mekaniks", "A community for wanna-be mekaniks"),
            Community(null, "Gardening", "A community for green thumbs"),
            Community(null, "Cooking", "A community for aspiring chefs"),
            Community(null, "Photography", "A community for shutterbugs"),
            Community(null, "Music", "A community for music lovers"),
            Community(null, "Rock", "ROOOOOOOOOOCK HELL YEAAAAAAAAAAAGH"),
            Community(null, "Electricians", "A community for breaking electrical stuff with loud explosions")
        )

        communities.forEach { communityService.createCommunity(it) }
    }

    private fun createDemoPostsAndComments() {
        createMekaniksPosts()
        createMekaniksPostWithComments()
    }

    private fun createMekaniksPostWithComments() {
        val post = PostDto(
            null,
            "Mekaniks",
            demoUser.name,
            "How do I fix my car?",
            "I have a 2002 Honda Civic and it's making a weird noise. Can anyone help me out?"
        )
        val id = postService.createPost(post)

        val comments = listOf(
            CommentDto("Have you tried turning it off and on again?", demoUser.name),
            CommentDto("I think you should check the engine", demoUser.name),
            CommentDto("Just buy a new car", demoUser.name),
            CommentDto("SELL IT AND BUY A BIKE", demoUser.name),
            CommentDto("No matter the car, the solution is always duct tape", demoUser.name)
        )

        comments.forEach { postService.createComment(id, it.comment, it.author!!) }
    }

    private fun createMekaniksPosts() {
        val posts = listOf(
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How big of a turbo can I fit on my Supra?",
                "Hey guys, I was wondering how big of a turbo I can fit on my 1998 Toyota Supra. I want to go fast!"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "The ultimate sleeper build?",
                "Greetings fellow mekaniks! I'm planning the ultimate sleeper build. What car should I use and what engine should I swap in?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "Is it possible to LS swap a Miata?",
                "I've been thinking about LS swapping my Miata. Is it possible? What do I need to do to make it work?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my oil?",
                "I've never changed my oil before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my brake pads?",
                "I've never changed my brake pads before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my spark plugs?",
                "I've never changed my spark plugs before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my air filter?",
                "I've never changed my air filter before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my tires?",
                "I've never changed my tires before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my brake fluid?",
                "I've never changed my brake fluid before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my transmission fluid?",
                "I've never changed my transmission fluid before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my differential fluid?",
                "I've never changed my differential fluid before. Can someone walk me through it?"
            ),
            PostDto(
                null,
                "Mekaniks",
                demoUser.name,
                "How do I change my coolant?",
                "I've never changed my coolant before. Can someone walk me through it?"
            )
        )

        posts.reversed().forEach { postService.createPost(it) }
    }
}