package com.neowise.snakerr

enum class Direction(val x: Int, val y: Int) {
    Up(0, -1),
    Down(0, 1),
    Left(-1, 0),
    Right(1, 0),
    None(0, 0)
}