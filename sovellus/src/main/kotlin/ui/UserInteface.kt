package ui

import db.AccountDao
import db.Database
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import domain.AccountManager

class UserInterface : Application() {
    var scene: Scene? = null
    var primaryStage: Stage? = null
    val database = Database("jdbc:sqlite:database.db")
    val accountDao = AccountDao(database.connection)
    val manager = AccountManager(accountDao)

    override fun init() {
        scene = LoginScene(manager, this).getScene()
    }

    override fun start(stage: Stage) {
        primaryStage = stage
        primaryStage?.scene = scene
        primaryStage?.show()
    }

    fun setLoginScene() {
        primaryStage?.scene = LoginScene(manager, this).getScene()
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

    fun setRegistrationScene() {
        primaryStage?.scene = NewAccountScene(this, manager).getScene()
    }
}