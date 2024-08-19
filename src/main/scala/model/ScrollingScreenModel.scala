package model

import scalafx.scene.layout.Pane

object ScrollingScreenModel {
  var offsetY: Double = 0 // This keeps track of the screen's vertical offset
  val scrollSpeed: Double = 0.5 // Slow upward scroll

  def scrollPane(pane: Pane): Unit = {
    offsetY -= scrollSpeed
    pane.translateY = offsetY // Move the pane up to simulate the background moving up
  }

  def isPlayerOutOfBounds(player: Player): Boolean = {
    player.shape.boundsInParent().getMinY < 0 // Check if the player is out of the top of the screen
  }
}
