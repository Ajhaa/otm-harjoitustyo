package ui

import javafx.application.Application
import javafx.stage.Stage

class UserInterface : Application() {

    override fun start(primaryStage: Stage) {
        val loginScreen = LoginScreen()
        primaryStage.scene = loginScreen.getScene()
        primaryStage.show()
    }

}