package ui

import domain.AccountManager
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class MatchHistory(val manager: AccountManager, val app: UserInterface) {

    fun getScene() : Scene {
        var screen = VBox()
        var list = ArrayList<String>()

        manager.getCurrentAccount()!!.results.forEach{
            n -> screen.children.add(Text(n.toString()))
        }

        val backButton = Button("back")
        backButton.setOnAction { app.setMainScene() }
        screen.children.add(backButton)

        return Scene(screen)
    }
}