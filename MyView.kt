package com.example.colortiles.View

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.colortiles.Game.Game
import com.example.colortiles.Game.GameWindow
import com.example.colortiles.R

class MyView(context: Context?) : View(context) {
    private val p = Paint()

    private val gridSize = 4
    private val game = Game(gridSize)

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val gameWindow = GameWindow(right, bottom, 100, 40, gridSize)
        game.startGame(gameWindow)
    }

    override fun onDraw(canvas: Canvas) {
        canvas?.apply {
            drawColor(Color.LTGRAY)

            for (tile in game.tiles) {
                p.color = tile.color
                drawRect(tile.x, tile.y, tile.width, tile.height, p)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        game.clickTiles(event?.x ?: 0f, event?.y ?: 0f)

        invalidate()

        if (game.isVictory()) {
            val toast = Toast.makeText(context, context.getString(R.string.win_text), Toast.LENGTH_LONG)
            toast.show()
        }

        return false
    }


}