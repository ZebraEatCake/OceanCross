package model

class Player(var x: Double, var y: Double) {
  // Movement methods
  def moveUp(): Unit = {
    y += 10
  }

  def moveDown(): Unit = {
    y -= 10
  }

  def moveLeft(): Unit = {
    x -= 10
  }

  def moveRight(): Unit = {
    x += 10
  }
}
