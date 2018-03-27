package ui

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox

class LoginScreen() {
    private var screen = VBox()

    init {
        val inputText = TextField()
        val anchor = AnchorPane(inputText)
        val button = Button("login")

        AnchorPane.setLeftAnchor(inputText, 40.0)
        AnchorPane.setRightAnchor(inputText, 40.0)

        screen = VBox(anchor, button)

        screen.spacing = 5.0
        screen.alignment = Pos.CENTER
    }

    fun getScene() : Scene {
        return Scene(this.screen, 256.0, 144.0)
    }


}