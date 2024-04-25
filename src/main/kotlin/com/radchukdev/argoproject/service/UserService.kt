package com.radchukdev.argoproject.service

import com.radchukdev.argoproject.data.entity.UserEntity
import com.radchukdev.argoproject.data.repository.UserRepository
import com.radchukdev.argoproject.data.rest.user.UserResponse
import com.radchukdev.argoproject.model.Role
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
  private val userRepository: UserRepository,
  private val encoder: PasswordEncoder
) {
  init {
    val userAdmin = userRepository.findByEmail("login1")?.email
    if (userAdmin == null){
      val initialUser = UserEntity(
        email = "login1",
        password = encoder.encode("pass1"),
        role = Role.ADMIN
      )
      userRepository.save(initialUser)
    }
    val userUser = userRepository.findByEmail("login2")?.email
    if (userUser == null){
      val initialUser = UserEntity(
        email = "login2",
        password = encoder.encode("pass2"),
        role = Role.USER
      )
      userRepository.save(initialUser)
    }
  }

  fun createUser(user: UserEntity): UserEntity? {
    val found = userRepository.findByEmail(user.email)

    return if (found == null) {
       val update = user.copy(
        id = user.id,
        email = user.email,
        password = encoder.encode(user.password),
        role = user.role
      )
      userRepository.save(update)
      //update
    } else null
  }

  fun findByUUID(uuid: UUID): UserResponse? =
    userRepository.findByUuid(uuid)

  fun findAll(): List<UserEntity> =
    userRepository.findAll()
      .toList()

  fun deleteByUUID(uuid: UUID): Boolean {
    userRepository.deleteByUuid(uuid)
    return userRepository.findByUuid(uuid) == null
  }
}
