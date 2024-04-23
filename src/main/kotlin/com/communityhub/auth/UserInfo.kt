package com.communityhub.auth

data class UserInfo(val name: String, val isAdmin: Boolean, val expirationDate: Long) {
    fun isExpired(): Boolean {
        return expirationDate < System.currentTimeMillis()
    }

    fun isAdminAuthorized(): Boolean {
        return isAdmin && !isExpired()
    }
}
