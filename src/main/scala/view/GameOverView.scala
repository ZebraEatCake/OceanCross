package view

import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text
import scalafx.geometry.Pos
import scalafx.stage.Stage

object GameOverView {

  def display(stage: Stage): Unit = {
    val gameOverText = new Text("Game Over") {
      style = "-fx-font-size: 48pt"
    }

    val vbox = new VBox {
      alignment = Pos.Center
      children = gameOverText
    }

    stage.scene = new Scene {
      content = vbox
    }
  }
}
