package org.example.database

import org.ktorm.database.Database

class DatabaseManager {
    private val host = "62.84.120.173"
    private val username = "postgres"
    private val password = "321"
    val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:postgresql://${host}:5432/postgres?user=${username}&password=${password}"
        ktormDatabase = Database.connect(jdbcUrl)
    }
}