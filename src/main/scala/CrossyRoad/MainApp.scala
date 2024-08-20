package CrossyRoad

import CrossyRoad.model.{GameModel, Player}
import CrossyRoad.view.{GameController, MainGameController}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.Includes._

object MainApp extends JFXApp {
  // Initialize the game model and controllers
  val player = new Player(200, 200)
  val gameModel = new GameModel(player)
  val playerController = new PlayerController(player)
  val gameController = new GameController(playerController, gameModel)

  // Load the RootLayout.fxml file
  val rootResource = getClass.getResource("/CrossyRoad/view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  // Initialize the MainGameController
  val gameViewController = new MainGameController(gameModel, gameController)

  // Set up the stage with the root layout
  stage = new PrimaryStage {
    title = "Crossy Road Game"
    scene = new Scene(roots)
  }

  // Show the main game view
  def showMainGame(): Unit = {
    val resource = getClass.getResource("/CrossyRoad/view/MainGame.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.setController(gameViewController)
    loader.load()
    val gameRoot = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(gameRoot)
  }

  // Call showMainGame to display the game
  showMainGame()
}
