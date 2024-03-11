package com.radchukdev.argoproject.data.rest


import com.radchukdev.argoproject.data.entity.AgroCar
import com.radchukdev.argoproject.data.repository.AgroCarRepository
import com.radchukdev.argoproject.data.repository.CarCategoryRepository
import com.radchukdev.argoproject.data.repository.DriverRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agro-cars")
class AgroCarController(@Autowired private val agroCarRepository: AgroCarRepository) {

    @GetMapping
    fun getAllAgroCars(): List<AgroCar> {
        return agroCarRepository.findAll()
    }

    @PostMapping
    fun addAgroCar(@RequestBody agroCar: AgroCar): ResponseEntity<AgroCar> {
        val savedAgroCar = agroCarRepository.save(agroCar)
        return ResponseEntity(savedAgroCar, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAgroCarById(@PathVariable id: Long): ResponseEntity<AgroCar> {
        val agroCar = agroCarRepository.findById(id)
        return if (agroCar.isPresent) {
            ResponseEntity(agroCar.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateAgroCar(@PathVariable id: Long, @RequestBody updatedAgroCar: AgroCar): ResponseEntity<AgroCar> {
        return agroCarRepository.findById(id).map { existingAgroCar ->
            val updated = existingAgroCar.copy(
                carNumber = updatedAgroCar.carNumber,
                carName = updatedAgroCar.carName,
                yearOfManufacture = updatedAgroCar.yearOfManufacture,
                tankVolume = updatedAgroCar.tankVolume,
                engineCapacity = updatedAgroCar.engineCapacity,
                categoryId = updatedAgroCar.categoryId,
                driverId = updatedAgroCar.driverId
            )
            ResponseEntity(agroCarRepository.save(updated), HttpStatus.OK)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }


    @DeleteMapping("/{id}")
    fun deleteAgroCar(@PathVariable id: Long): ResponseEntity<Unit> {
        return agroCarRepository.findById(id).map { agroCar ->
            agroCarRepository.delete(agroCar)
            ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}
