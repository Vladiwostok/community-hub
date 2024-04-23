package com.communityhub.model

import jakarta.persistence.*

@Entity
class Post (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false)
    val id: Long?,
    @ManyToOne @JoinColumn(name = "community_name", nullable = false)
    val community: Community,
    @ManyToOne @JoinColumn(name = "chubuser_name", nullable = false)
    val chubUser: CHubUser,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false, length = 2048)
    val content: String,
    @Column(nullable = false)
    val dateMillis: Long
)
