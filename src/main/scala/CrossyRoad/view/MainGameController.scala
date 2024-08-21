package CrossyRoad.view

import CrossyRoad.MainApp
import CrossyRoad.MainApp.{gameController, gameModel, showGameOver}
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import CrossyRoad.model.{GameModel, GameState, Obstacle, Player}
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.scene.control.{Alert, ButtonType, Label}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.text.Font
import scalafxml.core.macros.sfxml

@sfxml
class MainGameController(private var gameCanvas: Canvas, private var scoreLabel: Label) {

  def initialize(): Unit = {
    gameController.startGameLoop(update)
  }

  initialize()

  def update(): Unit = {
    if (gameModel.state == GameState.Playing) {
      updatePlayingState()
    } else {
      println("update")
      gameController.stopGameLoop()
    }
  }

  private def updatePlayingState(): Unit = {
    val graphicsContext = gameCanvas.graphicsContext2D
    val player = gameModel.player
    val viewTopLeftY = gameModel.viewTopLeftY
    val viewTopLeftX = 0

    graphicsContext.clearRect(0, 0, gameCanvas.width.value, gameCanvas.height.value)

    drawGameWorld(viewTopLeftX, viewTopLeftY)
    drawPlayer(player, viewTopLeftX, viewTopLeftY)
    drawObstacles(viewTopLeftX, viewTopLeftY)

    // Update the score label with the highest y-value
    updateScore()

    if (isPlayerOutOfBounds(player, viewTopLeftX, viewTopLeftY) || isPlayerCollidingWithObstacle(player)) {
      MainApp.showGameOver()
      gameModel.state = GameState.GameOver
    }

    printCoordinates(viewTopLeftX, viewTopLeftY, player)
  }

  private def drawGameWorld(viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = gameCanvas.graphicsContext2D
    graphicsContext.fill = Color.LightBlue
    graphicsContext.fillRect(0, 0, 400, 400)
  }

  private def drawPlayer(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = gameCanvas.graphicsContext2D
    graphicsContext.fill = Color.Green
    val playerFontSize = 20
    val playerX = player.x - viewTopLeftX
    val playerY = 400 - (player.y - viewTopLeftY)

    val font = new Font("Segoe UI Historic", playerFontSize)
    graphicsContext.setFont(font)
    graphicsContext.fillText("𓊝", playerX, playerY)
  }

  private def drawObstacles(viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = gameCanvas.graphicsContext2D
    graphicsContext.fill = Color.Red
    val obstacleFontSize = 20

    val font = new Font("Segoe UI Historic", obstacleFontSize)
    graphicsContext.setFont(font)

    gameModel.obstacles.foreach(obstacle => {
      val obstacleX = obstacle.x - viewTopLeftX
      val obstacleY = 400 - (obstacle.y - viewTopLeftY)

      graphicsContext.save()

      if (obstacle.direction == 1) {
        graphicsContext.scale(-1, 1)
        graphicsContext.translate(-2 * obstacleX - obstacleFontSize, 0)
      }

      graphicsContext.fillText("﹏\uD80C\uDC81﹏", obstacleX, obstacleY)
      graphicsContext.restore()
    })
  }

  private def updateScore(): Unit = {
    Platform.runLater {
      scoreLabel.text = s"Score: ${gameModel.highestY.toInt}"
    }
  }

  private def isPlayerOutOfBounds(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Boolean = {
    val playerX = player.x - viewTopLeftX
    val playerY = 400 - (player.y - viewTopLeftY)
    val playerSize = 20
    playerX + playerSize <= 0 || playerX >= 400 || playerY + playerSize <= 0 || playerY >= 400
  }

  private def isPlayerCollidingWithObstacle(player: Player): Boolean = {
    val playerSize = 20
    gameModel.obstacles.exists { obstacle =>
      val obstacleSize = 20
      val playerLeft = player.x
      val playerRight = player.x + playerSize
      val playerTop = player.y
      val playerBottom = player.y + playerSize

      val obstacleLeft = obstacle.x
      val obstacleRight = obstacle.x + obstacleSize
      val obstacleTop = obstacle.y
      val obstacleBottom = obstacle.y + obstacleSize

      playerRight > obstacleLeft &&
        playerLeft < obstacleRight &&
        playerBottom > obstacleTop &&
        playerTop < obstacleBottom
    }
  }

  private def printCoordinates(viewTopLeftX: Double, viewTopLeftY: Double, player: Player): Unit = {
    println(s"View Coordinates:")
    println(s"Bottom-left: ($viewTopLeftX, $viewTopLeftY)")
    println(s"Top-right: (${viewTopLeftX + 400}, ${viewTopLeftY + 400})")
    println(s"Player Position: (${player.x}, ${player.y})")
  }
}
