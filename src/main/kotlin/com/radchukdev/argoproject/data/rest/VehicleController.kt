package com.radchukdev.argoproject.data.rest


import com.radchukdev.argoproject.data.entity.CarCategory
import com.radchukdev.argoproject.data.entity.Vehicle
import com.radchukdev.argoproject.data.repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vehicle")
class VehicleController(@Autowired private val VehicleRepository: VehicleRepository) {

    @GetMapping
    fun getAllVehicles(): List<Vehicle> {
        return VehicleRepository.findAll()
    }
    @GetMapping("/count")
    fun getNumberOfVehicle(): Int {
        return VehicleRepository.findDistinctVehicleCount()
    }

    @GetMapping("/reg/{number}")
    fun getVehicleByRegistrationNumber(@PathVariable number: String): ResponseEntity<Vehicle> {
        val vehicle = VehicleRepository.findByRegistrationNumber(number)
        return if (vehicle != null) {
            ResponseEntity(vehicle, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun addVehicle(@RequestBody vehicle: Vehicle): ResponseEntity<Vehicle> {
        val savedVehicle = VehicleRepository.save(vehicle)
        return ResponseEntity(savedVehicle, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getVehicleById(@PathVariable id: Long): ResponseEntity<Vehicle> {
        val Vehicle = VehicleRepository.findById(id)
        return if (Vehicle.isPresent) {
            ResponseEntity(Vehicle.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateVehicle(@PathVariable id: Long, @RequestBody updatedVehicle: Vehicle): ResponseEntity<Vehicle> {
        return VehicleRepository.findById(id).map { existingVehicle ->
            val updated = existingVehicle.copy(
                make = updatedVehicle.make,
                model = updatedVehicle.model,
                year = updatedVehicle.year,
                registrationNumber = updatedVehicle.registrationNumber,
                currentLocation = updatedVehicle.currentLocation,
                fuelType = updatedVehicle.fuelType,
                tankVolume = updatedVehicle.tankVolume,
                engineCapacity = updatedVehicle.engineCapacity,
                currentStatus = updatedVehicle.currentStatus,
                categoryId = updatedVehicle.categoryId
            )
            ResponseEntity(VehicleRepository.save(updated), HttpStatus.OK)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }


    @DeleteMapping("/{id}")
    fun deleteVehicle(@PathVariable id: Long): ResponseEntity<Unit> {
        return VehicleRepository.findById(id).map { Vehicle ->
            VehicleRepository.delete(Vehicle)
            ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}
