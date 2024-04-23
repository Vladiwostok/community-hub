package com.communityhub.repository

import com.communityhub.model.Community
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityRepository : JpaRepository<Community, Long> {
    fun findByName(name: String): Community

    fun findAllByOrderByNameAsc(): List<Community>
}