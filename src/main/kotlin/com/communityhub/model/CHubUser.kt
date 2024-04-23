package com.communityhub.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class CHubUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false)
    val id: Long?,
    @Column(unique = true, nullable = false)
    val name: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val isAdmin: Boolean? = false,
    @Column(nullable = true)
    var jwtToken: String? = null
)
