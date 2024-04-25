package com.radchukdev.argoproject.service

import com.radchukdev.argoproject.data.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
  private val userRepository: UserRepository
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    val userEntity = userRepository.findByEmail(username)
      ?: throw UsernameNotFoundException("User not found with username: $username")

    return User.builder()
      .username(userEntity.email)
      .password(userEntity.password)
      .roles(userEntity.role.name)
      .build()
  }
}