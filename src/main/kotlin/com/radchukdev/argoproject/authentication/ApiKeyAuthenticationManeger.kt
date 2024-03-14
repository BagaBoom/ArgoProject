package com.radchukdev.argoproject.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class ApiKeyAuthenticationManeger @Autowired constructor(
    @Value("\${agro.api-access-keys}") private var apiKeys: String
): AuthenticationManager{
    override fun authenticate(authentication: Authentication): Authentication {
        var requestApiKey = try {
            BearerToken.fromString(authentication.principal as String).getAccessToken()
        }catch (e : Exception){
            e.printStackTrace()
            throw BadCredentialsException("The API Auth not value")
        }
        val listKeys = apiKeys.split(" ")

        authentication.isAuthenticated = requestApiKey.isNotBlank() && listKeys.contains(requestApiKey)
        return authentication
    }


}
