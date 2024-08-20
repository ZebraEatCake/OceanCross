package CrossyRoad.model

object GameState extends Enumeration {
  type GameState = Value
  val Playing, GameOver = Value
}

class GameModel(val player: Player) {
  import GameState._

  var state: GameState = Playing
  var viewTopLeftY: Double = 0.0
  private val scrollSpeed: Double = 5.0 // pixels per second
  private val viewHeight = 400
  private val middleY = viewHeight / 2

  def isGameOver: Boolean = state == GameOver

  def updateView(deltaTime: Double): Unit = {
    if (player.y >= viewTopLeftY + middleY) {
      viewTopLeftY += 20
    } else {
      viewTopLeftY += scrollSpeed * deltaTime
    }
    checkGameOver()
  }

  def checkGameOver(): Unit = {
    if (player.y < viewTopLeftY || player.y > viewTopLeftY + viewHeight) { // Assuming view height is 400
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