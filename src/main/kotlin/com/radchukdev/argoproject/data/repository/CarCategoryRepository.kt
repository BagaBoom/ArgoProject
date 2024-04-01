package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.CarCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface CarCategoryRepository : JpaRepository<CarCategory, Long>{
    @Query("SELECT c FROM CarCategory c WHERE c.name = :name")
    fun findByName(name: String): CarCategory?
}