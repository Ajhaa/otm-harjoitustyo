package ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage

class UserInterface : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.scene = Scene(VBox(), 256.0,144.0)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(UserInterface::class.java)
        }
    }
}