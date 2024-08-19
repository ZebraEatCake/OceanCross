package view

import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import model.GameModel

class GameOverView(val gameModel: GameModel, val canvas: Canvas) {
  def show(): Unit = {
    val gc = canvas.graphicsContext2D
    gc.fill = Color.Black
    gc.fillRect(0, 0, canvas.width.value, canvas.height.value)

    gc.fill = Color.Red
    gc.font = new scalafx.scene.text.Font(40)
    gc.fillText("Game Over", 100, 200)

    gc.font = new scalafx.scene.text.Font(20)
    gc.fillText("Press R to restart", 130, 250)
  }
}