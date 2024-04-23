package com.communityhub.dto

data class PostDto (
    val communityName: String,
    var chubUserName: String?,
    val title: String,
    val content: String,
)
