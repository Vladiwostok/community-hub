package com.communityhub.dto

data class PostWithCommentsDto(val post: PostDto, val comments: List<CommentDto>)
