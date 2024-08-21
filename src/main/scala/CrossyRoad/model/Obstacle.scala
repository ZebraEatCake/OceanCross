package CrossyRoad.model

class Obstacle(var x: Double, var y: Double, var direction: Int) {
  def move(deltaX: Double, deltaY: Double): Unit = {
    x += direction * deltaX
    y += deltaY
  }
}
