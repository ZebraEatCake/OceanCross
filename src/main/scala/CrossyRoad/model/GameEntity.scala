package CrossyRoad.model

trait GameEntity {
  var x: Double
  var y: Double
  def move(deltaX: Double, deltaY: Double): Unit
}
