package com.neowise.snakerr

class Map {

    private val data = Array(10) { y ->
        Array(10) { x-> Cell(x, y, 0) }
    }

    fun set(x: Int, y: Int, value: Int) {
        val cell = data[x][y]
        cell.value = value
    }

    fun get(x: Int, y: Int) : Int {
        return data[x][y].value
    }

    fun freeCells() : List<Cell> {

        val cells = mutableListOf<Cell>()

        for(y in 0..9) {
            for(x in 0..9) {
                val cell = data[x][y]
                if (cell.value == 0) {
                    cells.add(cell)
                }
            }
        }
        return cells
    }

    fun clear() {
        for(y in 0..9) {
            for(x in 0..9) {
                val cell = data[x][y]
                cell.value = 0
            }
        }
    }
}