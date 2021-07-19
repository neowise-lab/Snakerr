package com.neowise.snakerr

class Snake {

    val head = Chapter(0, 0)
    var last = head

    var lastX = 0
    var lastY = 0

    fun addChapter() {
        val chapter = Chapter(lastX, lastY)
        last.next = chapter
        last = chapter
    }

    fun move(x: Int, y: Int) {
        lastX = last.x
        lastY = last.y

        head.moveTo(head.x + x, head.y + y)
    }

    fun moveTo(x: Int, y: Int) {
        head.moveTo(x, y)
    }
}

class Chapter(var x: Int, var y: Int) {
    var next: Chapter? = null

    fun moveTo(newX: Int, newY: Int) {

        next?.moveTo(x, y)
        this.x = newX
        this.y = newY
    }
}