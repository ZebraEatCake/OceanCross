package CrossyRoad.view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.Button
import CrossyRoad.MainApp

@sfxml
class GameOverController(private val restartButton: Button) {
  print("Testing")

  def handleRestart(): Unit = {
    MainApp.gameModel.restart()
    MainApp.showMainGame()
  }
}