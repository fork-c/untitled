package org.example.repository

import org.example.database.DBUserTable
import org.example.database.DatabaseManager
import org.example.entities.User
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DBUserRepository : UserRepository {
    private val databaseManager = DatabaseManager()
    override suspend fun getAll(): List<User> {

        println(databaseManager.ktormDatabase.sequenceOf(DBUserTable).toList())
        return databaseManager.ktormDatabase.sequenceOf(DBUserTable).toList().map { User(it.email, it.name, it.surname, it.salary, it.phone, it.cname) }
    }
//
//
    override suspend fun getOne(email: String): User? {
        return databaseManager.ktormDatabase.sequenceOf(DBUserTable).toList().firstOrNull { it.email == email }?.let { User(it.email, it.name, it.surname, it.salary, it.phone, it.cname) }
    }

    override suspend fun update(email: String, user: User): User? {
        databaseManager.ktormDatabase.sequenceOf(DBUserTable).toList().firstOrNull { it.email == email }?.let {
            delete(email)
        }
        create(user)
        return user
    }

    override suspend fun create(user: User): User? {
        databaseManager.ktormDatabase.insert(DBUserTable) {
            set(DBUserTable.email, user.email)
            set(DBUserTable.name, user.name)
            set(DBUserTable.surname, user.surname)
            set(DBUserTable.salary, user.salary)
            set(DBUserTable.phone, user.phone)
            set(DBUserTable.cname, user.cname)
        }
        return user
    }

    override suspend fun delete(email: String): Boolean {
        val cur = databaseManager.ktormDatabase.delete(DBUserTable) {
            it.email eq email
        }
        return true
    }
}