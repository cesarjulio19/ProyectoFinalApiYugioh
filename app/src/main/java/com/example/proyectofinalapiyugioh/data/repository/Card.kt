package com.example.proyectofinalapiyugioh.data.repository

data class Card(val id: Int,
                val name: String,
                val type: String,
                val desc: String,
                val archetype: String,
                val imageUrl: String,
                val imageUrlSmall: String,
                val level: Int,
                val atk: Int,
                val def: Int)
