package org.example.repository

import org.example.entities.User

class UserInMemoryRepository: UserRepository {
    private val users = mutableListOf(User("jogn.doe@example.com", "hohn", "doe", 55000, "123-123-1231", "USA"))
    override suspend fun getAll(): List<User> {
        return users
    }

    override suspend fun getOne(email: String): User? {
        return users.firstOrNull { it.email == email }
    }

    override suspend fun update(email: String, user: User): User? {
        users.firstOrNull { it.email == email }?.let { users.remove(it) }
        users.add(user)
        return user
    }

    override suspend fun create(user: User): User? {
        users.add(user);
        return user
    }

    override suspend fun delete(email: String): Boolean {
        return users.firstOrNull { it.email == email }?.let {
            users.remove(it)
            true
        } ?: false
    }

}