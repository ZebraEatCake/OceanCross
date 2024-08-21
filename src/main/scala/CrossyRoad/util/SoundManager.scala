package CrossyRoad.util

import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.Includes._

class SoundManager {
  private var mediaPlayer: MediaPlayer = _

  def playBackgroundMusic(): Unit = {
    val musicFile = getClass.getResource("/Music/bgm.mp3").toExternalForm
    val media = new Media(musicFile)
    mediaPlayer = new MediaPlayer(media) {
      cycleCount = MediaPlayer.Indefinite
    }
    mediaPlayer.volume = 0.5
    mediaPlayer.play()
  }

  def stopBackgroundMusic(): Unit = {
    if (mediaPlayer != null) {
      mediaPlayer.stop()
    }
  }
}
