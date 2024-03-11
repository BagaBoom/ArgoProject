package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long>