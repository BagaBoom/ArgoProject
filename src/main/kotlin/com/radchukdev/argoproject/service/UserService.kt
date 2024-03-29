package com.radchukdev.argoproject.service

import com.radchukdev.argoproject.data.repository.UserRepository
import com.radchukdev.argoproject.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
  private val userRepository: UserRepository
) {

  fun createUser(user: User): User? {
    val found = userRepository.findByEmail(user.email)

    return if (found == null) {
      userRepository.save(user)
      user
    } else null
  }

  fun findByUUID(uuid: UUID): User? =
    userRepository.findByUUID(uuid)

  fun findAll(): List<User> =
    userRepository.findAll()
      .toList()

  fun deleteByUUID(uuid: UUID): Boolean =
    userRepository.deleteByUUID(uuid)
}
