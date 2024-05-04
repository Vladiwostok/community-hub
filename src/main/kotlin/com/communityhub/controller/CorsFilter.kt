package com.communityhub.controller

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

@WebFilter("/*")
class CorsFilter : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val res = response as HttpServletResponse
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
        chain?.doFilter(request, response)
    }
}