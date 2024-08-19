package controller

import model.GameModel
import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GameController(val playerController: PlayerController, val gameModel: GameModel) {
  private val executor = Executors.newScheduledThreadPool(1)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(executor)

  private var viewTopLeftY = 0.0
  private val viewHeight = 400.0 // Assuming this is the height of your view

  def getViewTopLeftY: Double = viewTopLeftY

  def processInput(key: String): Unit = {
    if (!gameModel.isGameOver) {
      playerController.processInput(key)
      gameModel.checkGameOver(viewTopLeftY, viewHeight)
    }
  }

  def startGameLoop(updateView: () => Unit): Unit = {
    executor.scheduleAtFixedRate(() => {
      if (!gameModel.isGameOver) {
        viewTopLeftY += 5 // Move view upwards
        gameModel.checkGameOver(viewTopLeftY, viewHeight)
      }
      updateView()
    }, 0, 1, TimeUnit.SECONDS)
  }

  def stopGameLoop(): Unit = {
    executor.shutdown()
  }
}