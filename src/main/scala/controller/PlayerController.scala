package controller

import model.Player

class PlayerController(val player: Player) {

  def processInput(key: String): Unit = {
    key match {
      case "W" => player.y += 10 // Move up; y increases
      case "S" => player.y -= 10 // Move down; y decreases
      case "A" => player.x -= 10 // Move left
      case "D" => player.x += 10 // Move right
      case _ => // Do nothing for other keys
    }
  }
}
