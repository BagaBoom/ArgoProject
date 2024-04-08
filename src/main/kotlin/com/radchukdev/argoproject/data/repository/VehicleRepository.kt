package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface VehicleRepository : JpaRepository<Vehicle, Long>{
    @Query("SELECT v FROM Vehicle v WHERE v.registrationNumber = :number")
    fun findByRegistrationNumber(number: String): Vehicle?


    @Query("select count(v) from Vehicle v")
    fun findDistinctVehicleCount(): Int


}