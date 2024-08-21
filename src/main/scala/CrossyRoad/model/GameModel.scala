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
  private val scrollSpeed: Double = 5.0 // pixels per second
  private val viewHeight = 400
  private val middleY = viewHeight / 2
  private val obstacleGenerationChance: Double = 0.3
  private var lastObstacleY: Double = 0.0

  var obstacle: List[Obstacle] = List(
    new Obstacle(200, 200),
    new Obstacle(300, 300)
    // Initial obstacles
  )

  def isGameOver: Boolean = state == GameOver

  def updateView(deltaTime: Double): Unit = {
    if (player.y >= viewTopLeftY + middleY) {
      viewTopLeftY += 20

      // Check if a new obstacle should be generated
      if ((player.y - lastObstacleY) >= 20 && Random.nextDouble() < obstacleGenerationChance) {
        generateObstacle()
        lastObstacleY = player.y
      }
    } else {
      viewTopLeftY += scrollSpeed * deltaTime
    }

    // Update obstacles
    obstacle.foreach(obstacle => obstacle.move(-1, -scrollSpeed * deltaTime))
  }

  private def generateObstacle(): Unit = {
    val newObstacleX = Random.nextInt(400)
    val newObstacleY = viewTopLeftY + viewHeight
    obstacle = new Obstacle(newObstacleX, newObstacleY) :: obstacle
  }

  def restart(): Unit = {
    MainApp.gameController.stopGameLoop()
    state = Playing
    viewTopLeftY = 0.0
    player.x = 200
    player.y = 200
    lastObstacleY = 0.0
    obstacle = List(
      new Obstacle(200, 200),
      new Obstacle(300, 300)
    )
  }
}
