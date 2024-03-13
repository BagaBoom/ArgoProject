package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "Driver")
data class Driver(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val licenseNumber: String,
    val statusDriver : String,
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    val vehicle: Vehicle?
) {
    constructor() : this(null, "","","","",null) {

    }
}