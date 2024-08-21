package CrossyRoad.model

class Obstacle(var x: Double, var y: Double, var direction: Int) extends GameEntity {
  override def move(deltaX: Double, deltaY: Double): Unit = {
    x += direction * deltaX
    y += deltaY
  }
}
