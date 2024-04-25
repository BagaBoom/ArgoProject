package com.radchukdev.argoproject.data.entity

import com.radchukdev.argoproject.model.Role
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    val email: String,

    val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role
) {
    constructor() : this(null, "","",Role.USER) {

    }
}
