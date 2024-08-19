package model

object GameState extends Enumeration {
  type GameState = Value
  val Playing, GameOver = Value
}

class GameModel(val player: Player) {
  import GameState._

  var state: GameState = Playing
  var viewTopLeftY: Double = 0.0
  private val scrollSpeed: Double = 5.0 // pixels per second

  def isGameOver: Boolean = state == GameOver

  def updateView(deltaTime: Double): Unit = {
    viewTopLeftY += scrollSpeed * deltaTime
    checkGameOver()
  }

  def checkGameOver(): Unit = {
    if (player.y < viewTopLeftY || player.y > viewTopLeftY + 400) { // Assuming view height is 400
      state = GameOver
    }
  }

  def restart(): Unit = {
    state = Playing
    viewTopLeftY = 0.0
    player.x = 200
    player.y = 200
  }
}