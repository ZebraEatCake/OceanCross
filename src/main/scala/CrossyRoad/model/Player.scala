package CrossyRoad.model

class Player(var x: Double, var y: Double) extends GameEntity {
  override def move(deltaX: Double, deltaY: Double): Unit = {
    x += deltaX
    y += deltaY
  }

  // Movement methods
  def moveUp(): Unit = {
    move(0, 20)
  }

  def moveDown(): Unit = {
    move(0, -20)
  }

  def moveLeft(): Unit = {
    move(-20, 0)
  }

  def moveRight(): Unit = {
    move(20, 0)
  }
}