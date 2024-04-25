package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.UserEntity
import com.radchukdev.argoproject.data.rest.user.UserResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.UUID


interface UserRepository : JpaRepository<UserEntity, UUID>{

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    fun findByEmail(email: String): UserEntity?

    @Query("SELECT u FROM UserEntity u")
    override fun findAll(): List<UserEntity>

    @Query("SELECT u FROM UserEntity u WHERE u.id = :uuid")
    fun findByUuid(uuid: UUID): UserResponse?

    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.id = :uuid")
    fun deleteByUuid(uuid: UUID)
}
