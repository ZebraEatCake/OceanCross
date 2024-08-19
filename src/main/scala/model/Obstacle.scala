package model

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

case class Obstacle(xPos: Double, yPos: Double) {
  val shape: Rectangle = new Rectangle {
    width = GameModel.gridSize
    height = GameModel.gridSize
    x = xPos
    y = yPos
    fill = Color.Red
  }

  def moveLeft(): Unit = {
    shape.x.value -= GameModel.gridSize
    if (shape.x.value < 0) {
      shape.x.value = GameModel.screenWidth // Reset to the right side if off-screen
    }
  }
}
