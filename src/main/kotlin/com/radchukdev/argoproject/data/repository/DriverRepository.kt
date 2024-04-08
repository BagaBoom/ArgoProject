package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DriverRepository : JpaRepository<Driver, Long> {

    @Query("select count(d) from Driver d")
    fun findDistinctDrivesCount(): Int
}