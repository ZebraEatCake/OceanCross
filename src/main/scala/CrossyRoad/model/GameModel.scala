package CrossyRoad.model

import CrossyRoad.MainApp
import scala.util.Random

object GameState extends Enumeration {
  type GameState = Value
  val Playing, GameOver = Value
}

class GameModel(val player: Player) {
  import GameState._

  var state: GameState = Playing
  var viewTopLeftY: Double = 0.0
  var scrollSpeed: Double = 8.0 // pixels per second
  private val viewHeight = 400
  private val middleY = viewHeight / 2
  private val obstacleGenerationChance: Double = 0.3
  private val maxObstaclesPerRow: Int = 3 // Maximum number of obstacles per row
  private var lastObstacleY: Double = 0.0

  var highestY: Double = player.y

  var obstacles: List[Obstacle] = List(
    new Obstacle(200, 200, 1),
    new Obstacle(300, 300, -1)
    // Initial obstacles with random directions
  )

  def isGameOver: Boolean = state == GameOver

  def updateView(deltaTime: Double): Unit = {
    if (player.y >= viewTopLeftY + middleY) {
      viewTopLeftY += 20

      if ((player.y - lastObstacleY) >= 20) {
        generateObstacles()
        lastObstacleY = player.y
      }
    } else {
      viewTopLeftY += scrollSpeed * deltaTime
    }

    // Update the highest y-value if the player moves upward
    if (player.y > highestY) {
      highestY = player.y
    }

    obstacles.foreach(obstacle => obstacle.move(1.0, -scrollSpeed * deltaTime))
  }

  private def generateObstacles(): Unit = {
    val numberOfObstacles = Random.nextInt(maxObstaclesPerRow) + 1
    val obstaclePositions = (1 to numberOfObstacles).map { _ =>
      val newObstacleX = Random.nextInt(400)
      val newObstacleY = viewTopLeftY + viewHeight
      val direction = if (Random.nextBoolean()) 1 else -1 // Randomly assign left (-1) or right (1) direction
      new Obstacle(newObstacleX, newObstacleY, direction)
    }
    obstacles = obstaclePositions.toList ::: obstacles
  }

  def restart(): Unit = {
    MainApp.gameController.stopGameLoop()
    state = Playing
    viewTopLeftY = 0.0
    player.x = 200
    player.y = 40
    lastObstacleY = 0.0
    highestY = player.y
    obstacles = List(
      new Obstacle(200, 200, 1),
      new Obstacle(300, 300, -1)
    )
  }
}
