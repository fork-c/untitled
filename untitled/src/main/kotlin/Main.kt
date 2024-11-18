package org.example
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.example.entities.User
import org.example.repository.DBUserRepository
import org.example.repository.UserInMemoryRepository
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(Thymeleaf) {
            setTemplateResolver(ClassLoaderTemplateResolver().apply {
                prefix = "templates/"
                suffix = ".html"
                characterEncoding = "utf-8"
            })
        }
        val userRepo = DBUserRepository()
        routing {
            get("/") {
                call.respond(ThymeleafContent("index", emptyMap()))
            }
            get("/users") {
                val email = call.parameters["email"]
                val phone = call.parameters["phone"]
                val name = call.parameters["name"]
                val surname = call.parameters["surname"]
                val salary = call.parameters["salary"]?.toInt()
                val cname = call.parameters["cname"]
                val type = call.parameters["type"]
                if (type == "create") {
                    email?.let {
                        userRepo.getOne(email)?.let {
                            userRepo.update(email, User(email, name!!, surname!!, salary!!, phone!!, cname!!))
                        } ?: userRepo.create(User(email, name!!, surname!!, salary!!, phone!!, cname!!))
                    }
                } else {
                    email?.let {
                        userRepo.delete(email)
                    }
                }
                call.respond(ThymeleafContent("users", mapOf("users" to userRepo.getAll())))
            }
            get("/create_user") {
                call.respond(ThymeleafContent("create_user", emptyMap()))
            }
            get("/delete_user") {
                call.respond(ThymeleafContent("delete_user", emptyMap()))
            }

//            get("") {
//
//            }
//            get("") {
//
//            }
        }
    }.start(wait = true)
}
