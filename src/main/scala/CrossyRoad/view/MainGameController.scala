package CrossyRoad.view

import CrossyRoad.MainApp.{gameController, gameModel}
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import CrossyRoad.model.{GameModel, GameState, Player}
import scalafx.Includes._
import scalafxml.core.macros.sfxml

@sfxml
class MainGameController(private var gameCanvas: Canvas){

  def initialize(): Unit = {
    println("Initializing MainGameController")
    gameController.startGameLoop(update)
  }

  initialize()

  def update(): Unit = {
    if (gameModel.state == GameState.Playing) {
      updatePlayingState()
    } else {
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

    if (isPlayerOutOfBounds(player, viewTopLeftX, viewTopLeftY)) {
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
    val playerSize = 20
    val playerX = player.x - viewTopLeftX
    val playerY = 400 - (player.y - viewTopLeftY)
    graphicsContext.fillRect(playerX, playerY, playerSize, playerSize)
  }

  private def isPlayerOutOfBounds(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Boolean = {
    val playerX = player.x - viewTopLeftX
    val playerY = 400 - (player.y - viewTopLeftY)
    val playerSize = 20
    playerX + playerSize <= 0 || playerX >= 400 || playerY + playerSize <= 0 || playerY >= 400
  }

  private def printCoordinates(viewTopLeftX: Double, viewTopLeftY: Double, player: Player): Unit = {
    println(s"View Coordinates:")
    println(s"Bottom-left: ($viewTopLeftX, $viewTopLeftY)")
    println(s"Top-right: (${viewTopLeftX + 400}, ${viewTopLeftY + 400})")
    println(s"Player Position: (${player.x}, ${player.y})")
  }
}
