package CrossyRoad.view

import CrossyRoad.model.Player

class PlayerController(val player: Player) {
  private var lastActionTime: Long = 0
  private val cooldownMillis = 200

  def processInput(key: String): Unit = {
    val currentTime = System.currentTimeMillis()

    if (currentTime - lastActionTime >= cooldownMillis) {
      key match {
        case "W" => player.moveUp()
        case "S" => player.moveDown()
        case "A" => player.moveLeft()
        case "D" => player.moveRight()
        case _ =>
      }
      lastActionTime = currentTime
    }
  }
}