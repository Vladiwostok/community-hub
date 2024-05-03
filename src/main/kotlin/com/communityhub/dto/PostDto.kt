package com.communityhub.dto

data class PostDto (
    val postId: Long?,
    val communityName: String,
    var author: String?,
    val title: String,
    val content: String,
)
