package ui

import domain.AccountManager
import domain.Enums.Tier
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class NewAccountScene(val app: UserInterface, val manager: AccountManager) {
    fun getScene() : Scene {
        val registrationLabel = Label("Username")
        val registrationField = TextField()

        val rankLabel = Label("Rank")

        val tierDropdown = ComboBox<Tier>()
        tierDropdown.items.addAll(Tier.values())

        val phaseDropdown = ComboBox<Int>()
        phaseDropdown.items.addAll(1..5)

        val rankSelector = HBox(tierDropdown, phaseDropdown)

        val submitButton = Button("Register")
        submitButton.setOnAction {
            manager.createAccount(registrationField.text, tierDropdown.value, phaseDropdown.value)
        }

        val returnButton = Button("Back")
        returnButton.setOnAction { app.setLoginScene() }

        val screen = VBox(registrationLabel, registrationField, rankLabel, rankSelector, submitButton, returnButton)

        return Scene(screen)


    }
}