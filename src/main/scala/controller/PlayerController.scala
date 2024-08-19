package controller

import model.Player
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

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
        case _ => // Do nothing for other keys
      }
      lastActionTime = currentTime
    }
  }
}
