package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.AgroCar
import org.springframework.data.jpa.repository.JpaRepository


interface AgroCarRepository : JpaRepository<AgroCar, Long>