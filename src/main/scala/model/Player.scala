package model

class Player(var x: Double, var y: Double) {
  // Movement methods
  def moveUp(): Unit = {
    y += 20
  }

  def moveDown(): Unit = {
    y -= 20
  }

  def moveLeft(): Unit = {
    x -= 20
  }

  def moveRight(): Unit = {
    x += 20
  }
}
