package com.neowise.snakerr

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.event.KeyEvent

class SnakeGame : DrawComponent() {

    val Speed = 500

    val map = Map()
    val snake = Snake()
    val apple = Apple(0, 0)

    var direction = Direction.None
    var lastTime = -1L

    var score = 0

    override fun initialize() {
        println("init")
        snake.moveTo(5, 5)
        apple.x = 1
        apple.y = 2
    }

    override fun update() {
        if (lastTime == -1L) {
            lastTime = System.currentTimeMillis()
        }

        if (System.currentTimeMillis() - lastTime > Speed) {
            lastTime = System.currentTimeMillis()
            moveSnake(direction.x, direction.y)
        }
    }

    override fun paintComponent(g: Graphics) {
        drawBackground(g)
        drawApple(g)
        drawSnake(g)
        drawScore(g)
    }

    private fun drawScore(g: Graphics) {
        g.color = Color.black
        g.font = Font("Segoe UI", Font.PLAIN, 16)
        g.drawString("Score: $score", 520, 20)
    }

    override fun keyPressed(e: KeyEvent) {
        if (!isRun) {
            return
        }

        if (e.keyCode == KeyEvent.VK_UP) {
            direction = Direction.Up
        }
        if (e.keyCode == KeyEvent.VK_DOWN) {
            direction = Direction.Down
        }
        if (e.keyCode == KeyEvent.VK_LEFT) {
            direction = Direction.Left
        }
        if (e.keyCode == KeyEvent.VK_RIGHT) {
            direction = Direction.Right
        }
    }

    private fun moveSnake(x: Int, y: Int) {

        val newX = snake.head.x + x
        val newY = snake.head.y + y

        if (newX < 0 || newX > 10 || newY < 0 || newY > 10 || map.get(newX, newY) == 1) {
            stop()
            println("Game Over!")
            return
        }

        snake.move(x, y)

        fillMapSnakePosition()

        if (apple.x == snake.head.x && apple.y == snake.head.y) {
            snake.addChapter()
            map.set(snake.last.x, snake.last.y, 1)

            score++

            generateApple()
        }
    }

    private fun generateApple() {
        val freeCells = map.freeCells()
        val randomCell = freeCells.random()

        apple.x = randomCell.x
        apple.y = randomCell.y

        map.set(apple.x, apple.y, 2)
    }

    private fun fillMapSnakePosition() {
        map.clear()
        map.set(apple.x, apple.y, 2)

        var next = snake.head

        while(true) {
            map.set(next.x, next.y, 1)
            if (next.next == null) {
                break
            }
            next = next.next!!
        }
        map.set(snake.head.x, snake.head.y, 3)
    }

    private fun drawSnake(g: Graphics) {
        g.color = Color.white
        g.fillOval(snake.head.x * 50, snake.head.y * 50, 50, 50)

        var next = snake.head.next
        g.color = Color.orange

        while(next != null) {
            g.fillRect(next.x * 50, next.y * 50, 50, 50)
            next = next.next
        }
    }

    private fun drawApple(g: Graphics) {
        g.color = Color.red
        g.fillOval(apple.x * 50 + 7, apple.y * 50 + 7, 35, 35)
    }

    private fun drawBackground(g: Graphics) {
        var isOdd: Boolean
        var reverse = false
        for(y in 0..9) {
            isOdd = reverse
            for(x in 0..9) {
                g.color = if (isOdd) Color(0x7cd60f) else Color(0x9fed40)
                g.fillRect(x * 50, y * 50, 50, 50)
                isOdd = !isOdd
            }
            reverse = !reverse
        }
    }
}