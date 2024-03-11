package com.radchukdev.argoproject.data.repository

import com.radchukdev.argoproject.data.entity.CarCategory
import org.springframework.data.jpa.repository.JpaRepository


interface CarCategoryRepository : JpaRepository<CarCategory, Long>