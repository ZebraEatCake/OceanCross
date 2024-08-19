package view

import model.GameModel
import scalafx.scene.layout.Pane

object GameView {
  def initialize(): Pane = {
    val pane = GameModel.initialize()
    pane.requestFocus() // Ensure the game pane has focus
    pane
  }
}
