package com.example.colortiles.Game

class GameWindow(w: Int, h: Int, val gamePadding: Int, val tilesPadding: Int, val gridSize: Int) {

    private val smallerSide = if (w < h) w else h
    private val biggerSide = if (w > h) w else h

    val tileSize =
        (smallerSide - gamePadding * 2 - tilesPadding * (gridSize - 1)) / gridSize

    val verticalPadding = (biggerSide - tilesPadding * (gridSize - 1) - tileSize * gridSize) / 2
}