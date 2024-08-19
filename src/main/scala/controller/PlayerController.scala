package controller

import model.Player

class PlayerController(val player: Player) {
  private val movementSpeed = 2 // Smaller movement per update

  def processInput(key: String): Unit = {
    key match {
      case "W" => player.y += movementSpeed
      case "S" => player.y -= movementSpeed
      case "A" => player.x -= movementSpeed
      case "D" => player.x += movementSpeed
      case _ => // Do nothing for other keys
    }
  }
}