package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "Driver")
data class Driver(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val drivingLicense: String
) {

    constructor() : this(null, "","","") {

    }
}