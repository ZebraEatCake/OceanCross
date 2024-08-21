package CrossyRoad

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.AnchorPane
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import CrossyRoad.model.{GameModel, Obstacle, Player}
import CrossyRoad.util.SoundManager
import CrossyRoad.view.{GameController, MainGameController, PlayerController}
import scalafx.Includes._
import javafx.{scene => jfxs}



object MainApp extends JFXApp {
  // Initialize the game model and controllers
  val player = new Player(200, 40)
  val gameModel = new GameModel(player)
  val playerController = new PlayerController(player)
  val gameController = new GameController(playerController, gameModel)
  val soundManager = new SoundManager()


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
  }

  def showHomePage(): Unit = {
    val resource = getClass.getResource("view/HomePage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showTutorial(): Unit = {
    val resource = getClass.getResource("view/Tutorial.fxml")
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

    onShown = handle {
      soundManager.playBackgroundMusic() // Play the music when the window is shown
    }

  }
    // Call HomePage to display the game
    showHomePage()

    def quit(): Unit = {
      gameController.shutdown()
      stage.close()
    }

}