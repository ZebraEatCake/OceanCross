package view

import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import model.{GameModel, Player, GameState}
import controller.GameController

class GameView(val gameModel: GameModel, val canvas: Canvas, val controller: GameController) {
  val viewWidth = 400
  val viewHeight = 400
  private val gameOverView = new GameOverView(gameModel, canvas)

  def show(stage: scalafx.application.JFXApp.PrimaryStage): Unit = {
    update()
  }

  def update(): Unit = {
    if (gameModel.state == GameState.Playing) {
      updatePlayingState()
    } else {
      gameOverView.show()
    }
  }

  private def updatePlayingState(): Unit = {
    val graphicsContext = canvas.graphicsContext2D
    val player = gameModel.player
    val viewTopLeftY = gameModel.viewTopLeftY
    val viewTopLeftX = 0

    graphicsContext.clearRect(0, 0, canvas.width.value, canvas.height.value)

    drawGameWorld(viewTopLeftX, viewTopLeftY)
    drawPlayer(player, viewTopLeftX, viewTopLeftY)

    if (isPlayerOutOfBounds(player, viewTopLeftX, viewTopLeftY)) {
      gameModel.state = GameState.GameOver
    }

    printCoordinates(viewTopLeftX, viewTopLeftY, player)
  }

  private def drawGameWorld(viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = canvas.graphicsContext2D
    graphicsContext.fill = Color.LightBlue
    graphicsContext.fillRect(0, 0, viewWidth, viewHeight)
  }

  private def drawPlayer(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = canvas.graphicsContext2D
    graphicsContext.fill = Color.Green
    val playerSize = 20
    val playerX = player.x - viewTopLeftX
    val playerY = viewHeight - (player.y - viewTopLeftY)
    graphicsContext.fillRect(playerX, playerY, playerSize, playerSize)
  }

  private def isPlayerOutOfBounds(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Boolean = {
    val playerX = player.x - viewTopLeftX
    val playerY = viewHeight - (player.y - viewTopLeftY)
    val playerSize = 20
    playerX + playerSize <= 0 || playerX >= viewWidth || playerY + playerSize <= 0 || playerY >= viewHeight
  }

  private def printCoordinates(viewTopLeftX: Double, viewTopLeftY: Double, player: Player): Unit = {
    val bottomLeftX = viewTopLeftX
    val bottomLeftY = viewTopLeftY
    val topRightX = viewTopLeftX + viewWidth
    val topRightY = viewTopLeftY + viewHeight
    println(s"View Coordinates:")
    println(s"Bottom-left: (${bottomLeftX}, ${bottomLeftY})")
    println(s"Top-right: (${topRightX}, ${topRightY})")
    println(s"Player Position: (${player.x}, ${player.y})")
  }
}