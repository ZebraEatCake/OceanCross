package view

import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import model.{GameModel, Player}
import controller.GameController

class GameView(val gameModel: GameModel, val canvas: Canvas, val controller: GameController) {
  val viewWidth = 400
  val viewHeight = 400

  private val gameOverView = new GameOverView(gameModel, canvas)

  def show(stage: scalafx.application.JFXApp.PrimaryStage): Unit = {
    update()
  }

  def update(): Unit = {
    if (gameModel.isGameOver) {
      gameOverView.show()
    } else {
      val graphicsContext = canvas.graphicsContext2D
      val player = gameModel.player

      val viewTopLeftY = controller.getViewTopLeftY
      val viewTopLeftX = 0

      graphicsContext.clearRect(0, 0, canvas.width.value, canvas.height.value)

      // Draw the visible portion of the game world
      drawGameWorld(viewTopLeftX, viewTopLeftY)

      // Draw the player
      drawPlayer(player, viewTopLeftX, viewTopLeftY)

      // Print the bottom-left and top-right coordinates of the view
      val bottomLeftX = viewTopLeftX
      val bottomLeftY = viewTopLeftY
      val topRightX = viewTopLeftX + viewWidth
      val topRightY = viewTopLeftY + viewHeight

      // Print view and player coordinates
      println(s"View Coordinates:")
      println(s"Bottom-left: (${bottomLeftX}, ${bottomLeftY})")
      println(s"Top-right: (${topRightX}, ${topRightY})")
      println(s"Player Position: (${player.x}, ${player.y})")
    }
  }

  private def drawGameWorld(viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = canvas.graphicsContext2D

    // Set a new background color or pattern
    graphicsContext.fill = Color.LightBlue // Example background color
    graphicsContext.fillRect(0, 0, viewWidth, viewHeight) // Draw background for the whole view area
  }

  private def drawPlayer(player: Player, viewTopLeftX: Double, viewTopLeftY: Double): Unit = {
    val graphicsContext = canvas.graphicsContext2D

    // Set the player color to green
    graphicsContext.fill = Color.Green

    // Draw the player as a rectangle
    val playerSize = 20

    // Calculate the player's position relative to the view
    val playerX = player.x - viewTopLeftX
    val playerY = viewHeight - (player.y - viewTopLeftY)

    // Ensure the player is within the view boundaries
    if (playerX + playerSize > 0 && playerX < viewWidth && playerY + playerSize > 0 && playerY < viewHeight) {
      graphicsContext.fillRect(playerX, playerY, playerSize, playerSize)
    } else {
      // Optionally, print debug info if the player is out of view
      println(s"Player out of view bounds: (${playerX}, ${playerY})")
    }
  }
}
