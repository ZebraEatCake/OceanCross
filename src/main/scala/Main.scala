import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import controller.{GameController, PlayerController}
import model.GameModel
import view.GameView
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.Includes._

object Main extends JFXApp {
  val canvas = new Canvas(400, 400)
  val player = new model.Player(200, 20)
  val gameModel = new GameModel(player)
  val playerController = new PlayerController(player)
  val gameController = new GameController(playerController, gameModel)
  val gameView = new GameView(gameModel, canvas, gameController)

  def restartGame(): Unit = {
    gameModel.restart()
    gameController.startGameLoop(() => gameView.update())
  }

  var keyPressed: Option[KeyCode] = None

  val timer = AnimationTimer { _ =>
    keyPressed.foreach { key =>
      gameController.processInput(key.toString)
    }
  }

  stage = new PrimaryStage {
    title = "Crossy Road Game"
    scene = new Scene {
      content = canvas

      onKeyPressed = (event: KeyEvent) => {
        keyPressed = Some(event.code)
        if (gameModel.isGameOver && event.code == KeyCode.R) {
          restartGame()
        }
      }

      onKeyReleased = (event: KeyEvent) => {
        keyPressed = None
      }
    }
  }

  gameView.show(stage)
  gameController.startGameLoop(() => gameView.update())
  timer.start()
}