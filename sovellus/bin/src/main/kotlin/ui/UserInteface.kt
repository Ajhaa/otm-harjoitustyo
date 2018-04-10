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
        
        val registrationField = TextField()
        val registrationAnchor = AnchorPane(registrationField)
        val registrationButton = Button("create profile")

        
        val loginField = TextField()
        val loginAnchor = AnchorPane(loginField)
        val loginButton = Button("login")

        val screen = VBox(registrationAnchor, registrationButton , loginAnchor, loginButton)

        loginButton.setOnAction {
            if (manager.login(loginField.text)) {
                primaryStage.scene = Scene(VBox(Text("logged in")), 256.0, 144.0)
            }
        }

        registrationButton.setOnAction {
            manager.createAccount(registrationField.text)
            registrationField.text = ""
        }

        AnchorPane.setLeftAnchor(loginField, 40.0)
        AnchorPane.setRightAnchor(loginField, 40.0)
        AnchorPane.setLeftAnchor(registrationField, 40.0)
        AnchorPane.setRightAnchor(registrationField, 40.0)



        screen.spacing = 5.0
        screen.alignment = Pos.CENTER
        primaryStage.scene = Scene(screen, 256.0, 144.0)
        primaryStage.show()
    }

}