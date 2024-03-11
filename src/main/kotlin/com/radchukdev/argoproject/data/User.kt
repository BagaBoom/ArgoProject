package com.radchukdev.argoproject.data

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String
) {
    constructor() : this(null, "", "") {

    }
}


