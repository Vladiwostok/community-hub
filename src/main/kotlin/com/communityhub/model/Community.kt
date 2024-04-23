package com.communityhub.model

import jakarta.persistence.*

@Entity
class Community (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false)
    val id: Long?,
    @Column(unique = true, nullable = false)
    val name: String,
    @Column(nullable = false, length = 1024)
    var description: String
)
