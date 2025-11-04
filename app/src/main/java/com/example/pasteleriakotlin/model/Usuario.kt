package com.example.pasteleriakotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val contrasena: String
)