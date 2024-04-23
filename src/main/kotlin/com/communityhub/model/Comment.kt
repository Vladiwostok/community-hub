package com.communityhub.model

import jakarta.persistence.*

@Entity
class Comment (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false)
    val id: Long?,
    @ManyToOne @JoinColumn(name = "post_id", nullable = false)
    val post: Post,
    @ManyToOne @JoinColumn(name = "chubuser_name", nullable = false)
    val chubUser: CHubUser,
    @Column(nullable = false, length = 1024)
    val content: String,
    @Column(nullable = false)
    val dateMillis: Long
)
