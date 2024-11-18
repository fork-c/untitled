package org.example.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBUserTable: Table<UserEntity>("users") {
    val email = varchar("email").primaryKey().bindTo { it.email }
    val name = varchar("name").bindTo { it.name }
    val surname = varchar("surname").bindTo { it.surname }
    val salary = int("salary").bindTo { it.salary }
    val phone = varchar("phone").bindTo { it.phone }
    val cname = varchar("cname").bindTo { it.cname }
}
interface UserEntity : Entity<UserEntity> {

    companion object : Entity.Factory<UserEntity>()

    val email: String
    val name: String
    val surname: String
    val salary: Int
    val phone: String
    val cname: String
}