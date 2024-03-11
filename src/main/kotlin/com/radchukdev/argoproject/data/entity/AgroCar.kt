package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "AgroCar")
data class AgroCar(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var carNumber: String,
    val carName: String,
    val yearOfManufacture: Int,
    val tankVolume: Int,
    val engineCapacity: Int,
    @ManyToOne
    @JoinColumn(name = "category_id")
    val categoryId: CarCategory,
    @ManyToOne
    @JoinColumn(name = "driver_id")
    val driverId: Driver
) {
    constructor(
    ) : this(null , "", "", 0, 0, 0,
        CarCategory(), Driver()
    ) {

    }
}
