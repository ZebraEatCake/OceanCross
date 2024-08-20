package CrossyRoad.view

import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import CrossyRoad.model.{GameModel, Player, GameState}

class MainGameController(gameModel: GameModel, gameController: GameController) {

  @FXML
  private var gameCanvas: Canvas = _

  @FXML
  def initialize(): Unit = {
    gameController.startGameLoop(update)
  }

  def update(): Unit = {
    if (gameModel.state == GameState.Playing) {
      updatePlayingState()
    } else {
      gameController.stopGameLoop()
    }
  }

  private def updatePlayingState(): Unit = {
    val graphicsContext = gameCanvas.getGraphicsContext2D
    val player = gameModel.player
    val viewTopLeftY = gameModel.viewTopLeftY
    val viewTopLeftX = 0

    graphicsContext.clearRect(0, 0, gameCanvas.getWidth, gameCanvas.getHeight)

    drawGameWorld(viewTopLeftX, viewTopLeftY)
    drawPlayer(player, viewTopLeftX, viewTopLeftY)

    if (isPlayerOutOfBounds(player, viewTopLeftX, viewTopLeftY)) {
      gameModel.state = GameState.GameOver
    }

    printCoordinates(viewTopLeftX, viewTopLeftY, player)
  }

  private def drawGameWorld(viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = gameCanvas.getGraphicsContext2D
    graphicsContext.setFill(Color.LIGHTBLUE)
    graphicsContext.fillRect(0, 0, 400, 400)
  }

  private def drawPlayer(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = gameCanvas.getGraphicsContext2D
    graphicsContext.setFill(Color.GREEN)
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
    val bottomLeftX = viewTopLeftX
    val bottomLeftY = viewTopLeftY
    val topRightX = viewTopLeftX + 400
    val topRightY = viewTopLeftY + 400
    println(s"View Coordinates:")
    println(s"Bottom-left: (${bottomLeftX}, ${bottomLeftY})")
    println(s"Top-right: (${topRightX}, ${topRightY})")
    println(s"Player Position: (${player.x}, ${player.y})")
  }
}