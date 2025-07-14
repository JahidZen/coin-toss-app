package com.khantech.toss_game

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PlayerRepository : CoroutineCrudRepository<Player, Long> {
    suspend fun findByPlayerName(playerName: String): Player?
    suspend fun findTop10ByOrderByTotalScoreDesc(): List<Player>
}