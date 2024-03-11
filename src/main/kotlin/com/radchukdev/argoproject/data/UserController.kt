package com.radchukdev.argoproject.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        try {
            val users = userRepository.findAll().toList()
            return ResponseEntity(users, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        try {
            val user = userRepository.findById(id)
                .orElseThrow { NoSuchElementException("User with id $id not found") }
            return ResponseEntity(user, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun addUser(@RequestBody user: User): ResponseEntity<User> {
        try {
            val savedUser = userRepository.save(user)
            return ResponseEntity(savedUser, HttpStatus.CREATED)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}

