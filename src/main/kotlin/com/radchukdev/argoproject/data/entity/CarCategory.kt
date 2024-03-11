package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "CarCategory")
data class CarCategory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var name: String
) {
    constructor() : this(null, "") {

    }
}
