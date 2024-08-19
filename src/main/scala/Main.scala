import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import controller.{GameController, PlayerController}
import model.GameModel
import view.GameView
import scalafx.scene.input.KeyEvent
import scalafx.Includes._

object Main extends JFXApp {
  val canvas = new Canvas(400, 400)
  val player = new model.Player(200, 200)
  val gameModel = new GameModel(player)
  val playerController = new PlayerController(player)
  val gameController = new GameController(playerController)
  val gameView = new GameView(gameModel, canvas, gameController)

  stage = new PrimaryStage {
    title = "Crossy Road Game"
    scene = new Scene {
      content = canvas

      // Set up key event handling
      onKeyPressed = (event: KeyEvent) => {
        val key = event.code.toString
        gameController.processInput(key)
      }
    }
  }

  gameView.show(stage)
  gameController.startGameLoop(() => gameView.update())
}
