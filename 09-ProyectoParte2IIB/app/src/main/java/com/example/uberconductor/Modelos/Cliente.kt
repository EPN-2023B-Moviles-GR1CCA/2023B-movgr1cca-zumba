package com.example.uberconductor.Modelos

import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()
data class Cliente(
    val id: String? = null,
    val nombre: String ? = null,
    val apellido: String ? = null,
    val correo: String ? = null,
    val telefono: String ? = null,
    val imagen: String ? = null,
    val token: String ? = null,
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Cliente>(json)
    }

}