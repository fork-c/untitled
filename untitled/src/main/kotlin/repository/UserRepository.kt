package org.example.repository

import org.example.entities.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun getOne(email: String): User?
    suspend fun update(email: String, user: User): User?
    suspend fun create(user: User): User?
    suspend fun delete(email: String): Boolean
}