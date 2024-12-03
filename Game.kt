package com.example.colortiles.Game

import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random


class Game(val gridSize: Int) {

    private lateinit var gameWindow: GameWindow

    private var grid = Array(gridSize) { BooleanArray(gridSize) { false } }

    var tiles = ArrayList<Tile>()

    private fun generateGrid() {

        while (true){
            for (i in grid.indices) {
                for (j in grid[i].indices) {
                    grid[i][j] = Random.nextBoolean()
                }
            }

            if (isVictory().not()) {
                break
            }
        }

        generateTiles()
    }


    private fun generateTiles() {
        val p = Paint()

        for (i in grid.indices) {
            for (j in grid[i].indices) {

                if (grid[i][j]) {
                    p.color = Color.CYAN
                } else {
                    p.color = Color.DKGRAY
                }

                tiles.add(
                    Tile(
                        gameWindow.gamePadding + i * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * i,
                        gameWindow.verticalPadding + j * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * j,
                        gameWindow.gamePadding + (i + 1) * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * i,
                        gameWindow.verticalPadding + (j + 1) * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * j,
                        p.color
                    )
                )
            }
        }
    }


    private fun updateColorTiles() {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j]) {
                    tiles[i * gridSize + j].color = Color.CYAN
                } else {
                    tiles[i * gridSize + j].color = Color.DKGRAY
                }
            }
        }
    }

    /**
     * Check if all tiles are compiled
     */
    fun isVictory(): Boolean {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (!grid[i][j]) {
                    return false
                }
            }
        }

        return true
    }

    /**
     * Get tile id
     */
    private fun getTileIndexes(touchX: Float, touchY: Float): Pair<Int, Int> {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (gameWindow.gamePadding + i * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * i <= touchX &&
                    touchX <= gameWindow.gamePadding + (i + 1) * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * i &&
                    gameWindow.verticalPadding + j * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * j <= touchY &&
                    touchY <= gameWindow.verticalPadding + (j + 1) * gameWindow.tileSize.toFloat() + gameWindow.tilesPadding * j
                ) {
                    return Pair(i, j)
                }
            }
        }
        return Pair(-1, -1)
    }

    /**
     * Tile click handling
     */
    fun clickTiles(touchX: Float, touchY: Float) {

        val tile = getTileIndexes(touchX, touchY)

        if (tile.first == -1 || tile.second == -1)
            return

        for (i in grid.indices) {
            grid[i][tile.second] = grid[i][tile.second].not()
            grid[tile.first][i] = grid[tile.first][i].not()
        }

        grid[tile.first][tile.second] = grid[tile.first][tile.second].not()

        updateColorTiles()
    }

    /**
     * Bootstrapping the game
     */
    fun startGame(gameWindow: GameWindow) {
        this.gameWindow = gameWindow
        generateGrid()
    }
}