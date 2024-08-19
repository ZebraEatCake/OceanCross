package controller

import model.{ScrollingScreenModel, GameModel, Player}
import scalafx.scene.layout.Pane

object ScrollingScreenController {

  def updateScrollingScreen(pane: Pane, player: Player): Boolean = {
    ScrollingScreenModel.scrollPane(pane)
    ScrollingScreenModel.isPlayerOutOfBounds(player)
  }
}
