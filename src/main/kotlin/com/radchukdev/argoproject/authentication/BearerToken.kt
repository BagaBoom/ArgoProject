package com.radchukdev.argoproject.authentication

data class BearerToken(private val accessToken: String){
    override fun toString() = "Bearer $accessToken"

    fun getAccessToken() = accessToken

    companion object{

        @Throws (IllegalArgumentException::class)
        fun fromString (token: String) : BearerToken {
            return if (token.startsWith("Bearer "))
            BearerToken(token.substring(7))
            else throw IllegalArgumentException("String is not a BearerToken")
        }
    }
}
