package CrossyRoad.view

import CrossyRoad.model.GameModel
import scalafx.application.Platform

import scala.concurrent.ExecutionContext
import java.util.concurrent.{Executors, ScheduledFuture, TimeUnit}

class GameController(val playerController: PlayerController, val gameModel: GameModel) {
  private val executor = Executors.newScheduledThreadPool(1)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(executor)

  private var lastUpdateTime: Long = System.nanoTime()

  def processInput(key: String): Unit = {
    if (!gameModel.isGameOver) {
      playerController.processInput(key)
    }
  }

  var future:ScheduledFuture[_] = null

  def startGameLoop(updateView: () => Unit): Unit = {
    future = executor.scheduleAtFixedRate(() => {
      val currentTime = System.nanoTime()
      val deltaTime = (currentTime - lastUpdateTime) / 1e9 // Convert to seconds
      lastUpdateTime = currentTime

      if (!gameModel.isGameOver) {
        Platform.runLater({
          gameModel.updateView(deltaTime)
        })
      }
      Platform.runLater({
        updateView()
      })
    }, 0, 16, TimeUnit.MILLISECONDS) // ~60 FPS
  }

  def shutdown(): Unit = {
    executor.shutdown()
  }

  def stopGameLoop(): Unit = {
    future.cancel(false)
  }
}