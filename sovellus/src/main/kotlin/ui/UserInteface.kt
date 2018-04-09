package ui

import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import domain.AccountManager
import javafx.scene.text.Text

class UserInterface : Application() {

    override fun start(primaryStage: Stage) {
        val manager = AccountManager()
        manager.createAccount("atte")

        val inputText = TextField()

        val anchor = AnchorPane(inputText)
        val button = Button("login")

        val screen = VBox(anchor, button)

        button.setOnAction {
            if (manager.login(inputText.text)) {
                primaryStage.scene = Scene(VBox(Text("logged in")), 256.0, 144.0)
            }
        }

        AnchorPane.setLeftAnchor(inputText, 40.0)
        AnchorPane.setRightAnchor(inputText, 40.0)


        screen.spacing = 5.0
        screen.alignment = Pos.CENTER
        primaryStage.scene = Scene(screen, 256.0, 144.0)
        primaryStage.show()
    }

}