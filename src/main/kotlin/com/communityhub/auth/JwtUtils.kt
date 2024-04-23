package com.communityhub.auth

import io.jsonwebtoken.Jwts
import java.util.*

private val jwtKey = Jwts.SIG.HS256.key().build()

fun createToken(name: String, isAdmin: Boolean): String {
    val oneHourFromNow = System.currentTimeMillis() + 3600000
    return Jwts.builder()
        .subject(name)
        .claim("isAdmin", isAdmin)
        .expiration(Date(oneHourFromNow))
        .signWith(jwtKey)
        .compact()
}

fun decodeToken(token: String): UserInfo {
    val claims = Jwts.parser()
        .verifyWith(jwtKey)
        .build()
        .parseSignedClaims(token)
        .payload

    return UserInfo(
        name = claims.subject,
        isAdmin = claims["isAdmin"] as Boolean,
        expirationDate = claims.expiration.time
    )
}
