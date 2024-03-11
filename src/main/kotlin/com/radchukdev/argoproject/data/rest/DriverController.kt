package com.radchukdev.argoproject.data.rest


import com.radchukdev.argoproject.data.entity.Driver
import com.radchukdev.argoproject.data.repository.DriverRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/drivers")
class DriverController(@Autowired private val driverRepository: DriverRepository) {

    @GetMapping
    fun getAllDrivers(): List<Driver> {
        return driverRepository.findAll()
    }

    @PostMapping
    fun addDriver(@RequestBody driver: Driver): ResponseEntity<Driver> {
        val savedDriver = driverRepository.save(driver)
        return ResponseEntity(savedDriver, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getDriverById(@PathVariable id: Long): ResponseEntity<Driver> {
        val driver = driverRepository.findById(id)
        return if (driver.isPresent) {
            ResponseEntity(driver.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateDriver(@PathVariable id: Long, @RequestBody updatedDriver: Driver): ResponseEntity<Driver> {
        return driverRepository.findById(id).map { existingDriver ->
            val updated = existingDriver.copy(
                firstName = updatedDriver.firstName,
                lastName = updatedDriver.lastName,
                drivingLicense = updatedDriver.drivingLicense
            )
            ResponseEntity(driverRepository.save(updated), HttpStatus.OK)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @DeleteMapping("/{id}")
    fun deleteDriver(@PathVariable id: Long): ResponseEntity<Unit> {
        return driverRepository.findById(id).map { driver ->
            driverRepository.delete(driver)
            ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}
