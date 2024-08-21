package CrossyRoad

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.AnchorPane
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import CrossyRoad.model.{GameModel, Player}
import CrossyRoad.view.{MainGameController, GameController, PlayerController}
import scalafx.Includes._
import javafx.{scene => jfxs}



object MainApp extends JFXApp {
  // Initialize the game model and controllers
  val player = new Player(200, 200)
  val gameModel = new GameModel(player)
  val playerController = new PlayerController(player)
  val gameController = new GameController(playerController, gameModel)

  // Load the RootLayout.fxml file
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.BorderPane]


  // Show the main game view
  def showMainGame(): Unit = {
    val resource = getClass.getResource("view/MainGame.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showGameOver(): Unit = {
    val resource = getClass.getResource("view/GameOver.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
    println("show game over")
  }

  def showTest(): Unit = {
    val resource = getClass.getResource("view/test.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }


  // Set up the stage with the root layout
  stage = new PrimaryStage {
    title = "Crossy Road Game"
    scene = new Scene(roots) {
      onKeyPressed = (event: scalafx.scene.input.KeyEvent) => {
        gameController.processInput(event.text.toUpperCase)
      }
    }
    onCloseRequest = handle {
      gameController.shutdown()
    }
  }


  // Call showMainGame to display the game
  showMainGame()
}