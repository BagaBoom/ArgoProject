package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository


interface VehicleRepository : JpaRepository<Vehicle, Long>