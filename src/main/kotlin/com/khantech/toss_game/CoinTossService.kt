package com.khantech.toss_game

import org.springframework.stereotype.Service
import kotlin.random.Random
import com.khantech.toss_game.Player


@Service
class CoinTossService(private val playerRepository: PlayerRepository) {
    suspend fun playGame(playerName: String, playerChoice: String, bet: Int): GameResult {

        val player = playerRepository.findByPlayerName(playerName) ?: Player(playerName = playerName)

        val serverToss = listOf<String>("Heads", "Tails").random()

        val gameResult = if (playerChoice.equals(serverToss, ignoreCase = true)) {
            player.totalScore +=  bet
            GameResult (
                outcomeMessage = "AI's move is $serverToss. You won $bet points, yayyy! \uD83C\uDF89 ",
                playerChoice = playerChoice,
                serverChoice = serverToss,
                pointsWonOrLost = bet
            )
        }
        else {
            player.totalScore -= bet
            GameResult (
                outcomeMessage = " AI's move is $serverToss. You lost $bet points. You're noob hihihi \uD83D\uDE02",
                playerChoice = playerChoice,
                serverChoice = serverToss,
                pointsWonOrLost = -bet

            )
        }
        playerRepository.save(player)
        return gameResult

    }

    suspend fun getLeaderboard(): List<Player> {
        return playerRepository.findTop10ByOrderByTotalScoreDesc()
    }
}