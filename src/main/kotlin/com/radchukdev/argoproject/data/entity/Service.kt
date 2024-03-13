package com.radchukdev.argoproject.data.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "Service")
data class Service(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val serviceDate: Date,
    val serviceType: String,
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    val vehicle: Vehicle?
) {
    constructor() : this(null,  Date(),"", null) {

    }

}