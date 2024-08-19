package controller

import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GameController(val playerController: PlayerController) {

  private val executor = Executors.newScheduledThreadPool(1)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(executor)

  private var viewTopLeftY = 0.0

  def getViewTopLeftY: Double = viewTopLeftY

  def processInput(key: String): Unit = {
    playerController.processInput(key)
  }

  def startGameLoop(updateView: () => Unit): Unit = {
    executor.scheduleAtFixedRate(() => {
      viewTopLeftY += 5 // Move view upwards
      updateView()
    }, 0, 1, TimeUnit.SECONDS)
  }

  def stopGameLoop(): Unit = {
    executor.shutdown()
  }
}
