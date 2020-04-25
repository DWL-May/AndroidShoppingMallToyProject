package com.example.parayo.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>{
    //중복검사
    fun findByEmail(email: String): User?
}