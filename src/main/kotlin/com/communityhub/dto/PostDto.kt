package com.communityhub.dto

data class PostDto (
    val communityName: String,
    var author: String?,
    val title: String,
    val content: String,
)
