package controller

import model.{GameModel, Player}
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.stage.Stage
import view.GameOverView
import scalafx.Includes._

object GameController {

  var timer: AnimationTimer = _

  def initializeGameView(): scalafx.scene.layout.Pane = {
    GameModel.initialize()
  }

  def startGameLoop(stage: Stage): Unit = {
    timer = AnimationTimer { _ =>
      val isOutOfBounds = ScrollingScreenController.updateScrollingScreen(GameModel.gamePane, GameModel.player)
      ObstacleController.moveObstacles()

      if (isOutOfBounds) {
        timer.stop() // Stop the timer when the game is over
        GameOverView.display(stage) // Display the game over screen
      }
    }
    timer.start() // Start the game loop

    // Handling key presses using ScalaFX
    GameModel.gamePane.onKeyPressed = (event: KeyEvent) => {
      PlayerController.handleInput(GameModel.player, event)
    }

    // Ensure the pane gets focus when the game loop starts
    GameModel.gamePane.requestFocus()
  }
}
