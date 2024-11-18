package org.example.repository

import org.example.entities.Patient

interface PatientRepository {
    suspend fun getAll(): List<Patient>
    suspend fun getOne(email: String): Patient?
    suspend fun create(patient: Patient): Patient
    suspend fun update(email: String, patient: Patient): Patient
    suspend fun delete(email: String): Boolean
}