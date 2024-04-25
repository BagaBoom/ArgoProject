package com.radchukdev.argoproject.data.rest.user


import com.radchukdev.argoproject.data.entity.UserEntity
import com.radchukdev.argoproject.model.Role
import com.radchukdev.argoproject.model.User
import com.radchukdev.argoproject.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/user")
class UserController(
  private val userService: UserService
) {

  @PostMapping
  fun create(@RequestBody userRequest: UserRequest): ResponseEntity<UserEntity> {
    val userEntity = userService.createUser(userRequest.toModel())
    return ResponseEntity.ok(userEntity)
  }

  @GetMapping
  fun listAll(): List<UserEntity> =
    userService.findAll()

  @GetMapping("/{uuid}")
  fun findByUUID(@PathVariable uuid: UUID): ResponseEntity<UserResponse> {
    val userEntity = userService.findByUUID(uuid)
      ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    return ResponseEntity.ok(userEntity)
  }

  @DeleteMapping("/{uuid}")
  fun deleteByUUID(@PathVariable uuid: UUID): ResponseEntity<Unit> {
    val success = userService.deleteByUUID(uuid)
    return if (success)
      ResponseEntity.noContent().build()
    else
      throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
  }

  private fun User.toResponse(): UserResponse =
    UserResponse(
      uuid = this.id,
      email = this.email,
    )

  private fun UserRequest.toModel(): UserEntity =
    UserEntity(
      email = this.email,
      password = this.password,
      role = Role.USER
    )
}