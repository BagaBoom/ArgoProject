package com.radchukdev.argoproject.data

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>