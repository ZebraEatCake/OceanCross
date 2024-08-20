package CrossyRoad.view

import scalafx.event.EventIncludes._
import scalafx.scene.input.KeyEvent
import scalafxml.core.macros.sfxml

@sfxml
class GameOverController(restartGame: () => Unit) {

  def handleKeyPress(event: KeyEvent): Unit = {
    event.code match {
      case scalafx.scene.input.KeyCode.R => restartGame()
      case _ => // Do nothing for other keys
    }
  }
}
