package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "Vehicle")
data class Vehicle(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val make: String,
    val model: String,
    val year: Int,
    val registrationNumber : String,
    val currentLocation : String,
    val fuelType : String,
    val tankVolume: Float,
    val engineCapacity: Float,
    val currentStatus: String,
    @ManyToOne
    @JoinColumn(name = "category_id")
    val categoryId: CarCategory?,
) {
    constructor(
    ) : this(null , "", "", 0, "",
        "","",0.0f,0.0f,"",
        null
    ) {

    }
}
