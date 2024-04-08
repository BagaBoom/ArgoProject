package com.radchukdev.argoproject.data.rest

import com.radchukdev.argoproject.data.entity.CarCategory
import com.radchukdev.argoproject.data.entity.Vehicle
import com.radchukdev.argoproject.data.repository.CarCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/car-categories")
class CarCategoryController(@Autowired private val repository: CarCategoryRepository) {

    @GetMapping
    fun getAllCategories(): List<CarCategory> {
        return repository.findAll().toList()
    }

    @GetMapping("/count")
    fun getNumberOfCategories(): ResponseEntity<Int> {
        val count = repository.findDistinctCategoryCount()
        return ResponseEntity.ok(count)
    }

    @GetMapping("/name/{name}")
    fun getCarCategoryByName(@PathVariable name: String): ResponseEntity<CarCategory> {
        val carCategory = repository.findByName(name)
        return if (carCategory != null) {
            ResponseEntity(carCategory, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }



    @PostMapping
    fun addCategory(@RequestBody category: CarCategory): ResponseEntity<CarCategory> {
        val savedCategory = repository.save(category)
        return ResponseEntity(savedCategory, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Unit> {
        repository.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody updatedCategory: CarCategory): ResponseEntity<CarCategory> {
        val existingCategory = repository.findById(id)
            .orElseThrow { NoSuchElementException("Car category with id $id not found") }

        existingCategory.name = updatedCategory.name

        val savedCategory = repository.save(existingCategory)
        return ResponseEntity(savedCategory, HttpStatus.OK)
    }
}