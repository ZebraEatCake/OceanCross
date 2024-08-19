package controller

import model.Player
import scalafx.Includes._
import scalafx.scene.input.{KeyEvent, KeyCode}

object PlayerController {

  def handleInput(player: Player, event: KeyEvent): Unit = {
    event.code match {
      case KeyCode.W => player.moveUp()
      case KeyCode.S => player.moveDown()
      case KeyCode.A => player.moveLeft()
      case KeyCode.D => player.moveRight()
      case _ => // Do nothing for other keys
    }
  }
}
