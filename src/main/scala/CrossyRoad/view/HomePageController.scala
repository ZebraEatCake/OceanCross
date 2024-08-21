package CrossyRoad.view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.Button
import CrossyRoad.MainApp

@sfxml
class HomePageController(private val playButton: Button) {

  def startGame(): Unit = {
    MainApp.showMainGame()
  }

  def quitGame():Unit = {
    MainApp.quit()
  }

  def showTutorial():Unit = {
    MainApp.showTutorial()
  }

}