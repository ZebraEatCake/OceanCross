package controller

import model.{Obstacle, GameModel}

object ObstacleController {

  // Define speeds for obstacles
  private val obstacleSpeeds: Map[Obstacle, Double] = GameModel.obstacles.map { obstacle =>
    obstacle -> 2.0 // Assign a default speed of 2.0 pixels per frame
  }.toMap

  def moveObstacles(): Unit = {
    GameModel.obstacles.foreach { obstacle =>
      moveObstacleLeft(obstacle)
    }
  }

  private def moveObstacleLeft(obstacle: Obstacle): Unit = {
    val speed = obstacleSpeeds(obstacle)
    obstacle.shape.x.value -= speed
    if (obstacle.shape.x.value < 0) {
      obstacle.shape.x.value = GameModel.screenWidth // Reset to the right side if off-screen
    }
  }
}
