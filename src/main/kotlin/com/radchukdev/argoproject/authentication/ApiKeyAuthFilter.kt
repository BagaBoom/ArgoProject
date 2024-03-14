package com.radchukdev.argoproject.authentication

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter


class ApiKeyAuthFilter : AbstractPreAuthenticatedProcessingFilter() {
    companion object{
        private const val HEADER = "KeyToken"
    }
    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any = try {
        request.getHeader(HEADER).orEmpty()
    }catch (e : Exception){
        e.printStackTrace()
        ""
    }
    override fun getPreAuthenticatedCredentials(request: HttpServletRequest): Any {
        return "N/A"
    }
}
