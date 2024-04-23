package com.communityhub

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition
class CommunityHubApplication

fun main(args: Array<String>) {
	runApplication<CommunityHubApplication>(*args)
}
