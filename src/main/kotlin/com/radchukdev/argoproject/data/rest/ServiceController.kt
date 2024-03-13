package com.radchukdev.argoproject.data.rest


import com.radchukdev.argoproject.data.entity.Service
import com.radchukdev.argoproject.data.repository.ServiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/services")
class ServiceController(@Autowired private val serviceRepository: ServiceRepository) {

    @GetMapping
    fun getAllServices(): List<Service> {
        return serviceRepository.findAll()
    }

    @PostMapping
    fun addService(@RequestBody service: Service): ResponseEntity<Service> {
        val savedService = serviceRepository.save(service)
        return ResponseEntity(savedService, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getServiceById(@PathVariable id: Long): ResponseEntity<Service> {
        val service = serviceRepository.findById(id)
        return if (service.isPresent) {
            ResponseEntity(service.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateService(@PathVariable id: Long, @RequestBody updatedService: Service): ResponseEntity<Service> {
        return serviceRepository.findById(id).map { existingService ->
            val updated = existingService.copy(
                serviceDate = updatedService.serviceDate,
                serviceType = updatedService.serviceType,
                vehicle = updatedService.vehicle
            )
            ResponseEntity(serviceRepository.save(updated), HttpStatus.OK)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @DeleteMapping("/{id}")
    fun deleteService(@PathVariable id: Long): ResponseEntity<Unit> {
        return serviceRepository.findById(id).map { service ->
            serviceRepository.delete(service)
            ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}
