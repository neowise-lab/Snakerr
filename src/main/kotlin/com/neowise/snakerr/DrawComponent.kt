package com.neowise.snakerr

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.lang.Thread.sleep
import javax.swing.JComponent

abstract class DrawComponent : JComponent(), KeyListener {

    protected var isRun = false

    private val thread = Thread {
        while(isRun) {
            update()
            repaint()
            sleep(10)
        }
    }

    fun start() {
        initialize()
        isRun = true
        thread.start()
    }

    fun stop() {
        isRun = false
    }

    abstract fun initialize()
    abstract fun update()

    override fun keyTyped(e: KeyEvent) {}
    override fun keyPressed(e: KeyEvent) {}
    override fun keyReleased(e: KeyEvent) {}
}