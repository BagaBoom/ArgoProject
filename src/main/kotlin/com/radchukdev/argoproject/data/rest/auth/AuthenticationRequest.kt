package com.radchukdev.argoproject.data.rest.auth

data class AuthenticationRequest(
  val email: String,
  val password: String,
)