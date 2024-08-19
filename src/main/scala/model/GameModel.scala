package model

object GameState extends Enumeration {
  type GameState = Value
  val Playing, GameOver = Value
}

class GameModel(val player: Player) {
  import GameState._

  var state: GameState = Playing

  def isGameOver: Boolean = state == GameOver

  def checkGameOver(viewTopLeftY: Double, viewHeight: Double): Unit = {
    if (player.y < viewTopLeftY || player.y > viewTopLeftY + viewHeight) {
      state = GameOver
    }
  }

  def restart(): Unit = {
    state = Playing
    player.x = 200 // Reset player position
    player.y = 200
  }
}