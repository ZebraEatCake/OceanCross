import controller.GameController
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

object Main extends JFXApp {
  stage = new PrimaryStage {
    title.value = "Crossy Road"
    width = 800
    height = 600
    scene = new Scene {
      content = GameController.initializeGameView()
    }
  }

  GameController.startGameLoop(stage)
}
