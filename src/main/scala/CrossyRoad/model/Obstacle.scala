package CrossyRoad.model

class Obstacle(var x: Double, var y: Double) {
  def move(dx: Double, dy: Double): Unit = {
    x += dx
    y += dy
  }
}