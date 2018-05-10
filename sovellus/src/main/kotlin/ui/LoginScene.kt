package ui

import domain.AccountManager
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox

class LoginScene(val manager: AccountManager, val app: UserInterface) {
    fun getScene(): Scene {

       // val registrationField = TextField()
       // val registrationAnchor = AnchorPane(registrationField)
        val registrationButton = Button("create profile")

        val loginField = TextField()
        val loginAnchor = AnchorPane(loginField)
        val loginButton = Button("login")

        val screen = VBox(loginAnchor, loginButton, registrationButton)
        val scene = Scene(screen, 256.0, 144.0)

        loginButton.setOnAction {
            if (manager.login(loginField.text)) {
                app.setMainScene()
            }
        }

        registrationButton.setOnAction {
            app.setRegistrationScene()
        }

        AnchorPane.setLeftAnchor(loginField, 40.0)
        AnchorPane.setRightAnchor(loginField, 40.0)


        screen.spacing = 5.0
        screen.alignment = Pos.CENTER
        return scene
    }
}