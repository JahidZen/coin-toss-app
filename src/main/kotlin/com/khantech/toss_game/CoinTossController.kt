package com.khantech.toss_game

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController // This tells Spring that this class will handle web requests
@RequestMapping("/game") // This is the basic endpoint for any web address related to this program
class CoinTossController(private val coinTossService: CoinTossService) {
    @PostMapping("/play")
    suspend fun coinTossController(@RequestBody request: PlayRequest): GameResult {
        return coinTossService.playGame(request.playerName, request.choice, request.bet)
    }

    @GetMapping("/leaderboard")
    suspend fun getLeaderboard(): List<Player> {
        return coinTossService.getLeaderboard()
    }

}

data class PlayRequest(val playerName: String, val choice: String, val bet: Int)

data class GameResult(
    val outcomeMessage: String,
    val playerChoice: String,
    val serverChoice: String,
    val pointsWonOrLost: Int

)