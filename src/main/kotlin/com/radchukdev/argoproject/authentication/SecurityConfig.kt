package com.radchukdev.argoproject.authentication

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    private val apiKeyAuthenticationManager: ApiKeyAuthenticationManeger
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain = with(http) {
        cors { it.disable() }
        csrf { it.disable() }

        sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            it.sessionConcurrency { concurrencycontrolconfigurer ->
                concurrencycontrolconfigurer.maximumSessions(1)
                concurrencycontrolconfigurer.maxSessionsPreventsLogin(false)
            }
        }
        authorizeHttpRequests {it
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
        }

        addFilter(ApiKeyAuthFilter().apply { setAuthenticationManager(apiKeyAuthenticationManager) })
    }.build()
}
