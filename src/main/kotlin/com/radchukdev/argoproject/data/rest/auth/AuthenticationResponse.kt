package com.radchukdev.argoproject.data.rest.auth

data class AuthenticationResponse(
  val accessToken: String,
  val refreshToken: String,
)