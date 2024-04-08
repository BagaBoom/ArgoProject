package com.radchukdev.argoproject.data.rest

import com.radchukdev.argoproject.data.repository.CarCategoryRepository
import com.radchukdev.argoproject.data.repository.DriverRepository
import com.radchukdev.argoproject.data.repository.VehicleRepository
import com.radchukdev.argoproject.model.InfoCountsObjest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/count")
class CountsObjectInfoConttroller (@Autowired private val repositoryCarCategory: CarCategoryRepository,
                                   @Autowired private val repositoryVehicleRepository: VehicleRepository,
                                   @Autowired private val repositoryDriverRepository: DriverRepository) {
    @GetMapping()
    fun getCount(): ResponseEntity<InfoCountsObjest> {
        val count : InfoCountsObjest = InfoCountsObjest(
            repositoryVehicleRepository.findDistinctVehicleCount(),
            repositoryCarCategory.findDistinctCategoryCount(),
            repositoryDriverRepository.findDistinctDrivesCount()
        )
        return ResponseEntity.ok(count)
    }
}