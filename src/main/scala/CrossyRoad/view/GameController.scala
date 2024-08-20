package CrossyRoad.view

import CrossyRoad.model.GameModel

import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GameController(val playerController: PlayerController, val gameModel: GameModel) {
  private val executor = Executors.newScheduledThreadPool(1)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(executor)

  private var lastUpdateTime: Long = System.nanoTime()

  def processInput(key: String): Unit = {
    if (!gameModel.isGameOver) {
      playerController.processInput(key)
    }
  }

  def startGameLoop(updateView: () => Unit): Unit = {
    executor.scheduleAtFixedRate(() => {
      val currentTime = System.nanoTime()
      val deltaTime = (currentTime - lastUpdateTime) / 1e9 // Convert to seconds
      lastUpdateTime = currentTime

      if (!gameModel.isGameOver) {
        gameModel.updateView(deltaTime)
      }
      updateView()
    }, 0, 16, TimeUnit.MILLISECONDS) // ~60 FPS
  }

  def stopGameLoop(): Unit = {
    executor.shutdown()
  }
}