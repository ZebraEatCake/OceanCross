package model

import scalafx.scene.layout.Pane

object GameModel {
  val screenWidth: Double = 800
  val screenHeight: Double = 600
  val gridSize: Double = screenWidth / 20

  val gamePane: Pane = new Pane()

  val player: Player = Player()

  val obstacles: Seq[Obstacle] = Seq(
    Obstacle(gridSize * 2, gridSize * 5),
    Obstacle(gridSize * 5, gridSize * 5)
  )

  def initialize(): Pane = {
    gamePane.children.add(player.shape)
    obstacles.foreach(obstacle => gamePane.children.add(obstacle.shape))
    gamePane
  }
}
