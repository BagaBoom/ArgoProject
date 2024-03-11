package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.Service
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceRepository : JpaRepository<Service, Long>