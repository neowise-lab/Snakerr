package com.neowise.snakerr

import javax.swing.JFrame

fun main(args: Array<String>) {
    val snakeGame = SnakeGame()

    val frame = JFrame()
    frame.setSize(600, 600)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.addKeyListener(snakeGame)
    frame.add(snakeGame)
    frame.isVisible = true
    ///
    snakeGame.start()
}