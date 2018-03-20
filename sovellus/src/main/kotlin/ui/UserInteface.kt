package ui

import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.stage.Stage

class UserInterface : Application() {

    override fun start(primaryStage: Stage) {
        val loginScreen = LoginScreen()
        primaryStage.scene = loginScreen.getScene()
        primaryStage.show()
    }

}