package CrossyRoad.view

import CrossyRoad.MainApp
import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml

@sfxml
class TutorialController(private val backButton: Button) {

  def showHomePage(): Unit = {
    MainApp.showHomePage()
  }


}