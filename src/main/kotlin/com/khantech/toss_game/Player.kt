package com.khantech.toss_game

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Players") // Use @Table from Spring Data, not JPA
data class Player(
    @Id // Use @Id from Spring Data
    val id: Long? = null, // Make id nullable for new, unsaved players

    val playerName: String,

    var totalScore: Int = 0
)
