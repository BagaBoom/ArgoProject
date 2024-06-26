package com.radchukdev.argoproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class ArgoProjectApplication

fun main(args: Array<String>) {
    runApplication<ArgoProjectApplication>(*args)
}
