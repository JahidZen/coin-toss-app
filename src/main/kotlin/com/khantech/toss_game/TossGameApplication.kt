package com.khantech.toss_game

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TossGameApplication

fun main(args: Array<String>) {
	runApplication<TossGameApplication>(*args)
}
