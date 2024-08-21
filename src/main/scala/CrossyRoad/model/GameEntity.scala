package CrossyRoad.model

abstract class GameEntity {
  var x: Double
  var y: Double

  // Abstract method
  def move(deltaX: Double, deltaY: Double): Unit
}
