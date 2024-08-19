package model

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

case class Player() {
  val shape: Rectangle = new Rectangle {
    width = GameModel.gridSize
    height = GameModel.gridSize
    x = (GameModel.screenWidth - GameModel.gridSize) / 2
    y = GameModel.screenHeight - GameModel.gridSize * 2
    fill = Color.Green
  }

  def moveUp(): Unit = {
    shape.y.value -= GameModel.gridSize
  }

  def moveDown(): Unit = {
    if (shape.y.value + GameModel.gridSize < GameModel.screenHeight) {
      shape.y.value += GameModel.gridSize
    }
  }

  def moveLeft(): Unit = {
    if (shape.x.value > 0) {
      shape.x.value -= GameModel.gridSize
    }
  }

  def moveRight(): Unit = {
    if (shape.x.value + GameModel.gridSize < GameModel.screenWidth) {
      shape.x.value += GameModel.gridSize
    }
  }
}
