package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "Service")
data class Service(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val serviceName: String,
    val serviceDate: Date,
    @ManyToOne
    @JoinColumn(name = "agro_car_id")
    val agroCar: AgroCar
) {
    constructor() : this(null, "", Date(), AgroCar(
    )) {

    }

}