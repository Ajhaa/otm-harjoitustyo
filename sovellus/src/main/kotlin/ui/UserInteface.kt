package ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import domain.AccountManager

class UserInterface : Application() {
    var scene: Scene? = null
    var primaryStage: Stage? = null
    val manager = AccountManager()

    override fun init() {
        scene = LoginScene(manager, this).getScene()
        //one hardcoded account for now, will delete later
        manager.createAccount("atte")
    }

    override fun start(stage: Stage) {
        primaryStage = stage
        primaryStage?.scene = scene
        primaryStage?.show()
    }

    fun setMainScene() {
        primaryStage?.scene = MainScene(manager, this).getScene()
    }

    fun setHistoryScene() {
        primaryStage?.scene = MatchHistory(manager, this).getScene()
    }

    fun setStatisticScene() {
        primaryStage?.scene = Statistics(manager, this).getScene()
    }
}