package ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import domain.AccountManager
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class UserInterface : Application() {
    var scene: Scene? = null
    var primaryStage: Stage? = null
    val manager = AccountManager()

    override fun init() {
        scene = LoginScene(manager, this).getScene()
    }

    override fun start(stage: Stage) {
        primaryStage = stage
        primaryStage?.scene = scene
        primaryStage?.show()
    }

    fun setMainScene() {
        primaryStage?.scene = MainScene(manager, this).getScene()
    }
}