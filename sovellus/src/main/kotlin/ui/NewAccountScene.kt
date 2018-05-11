package ui

import domain.AccountManager
import domain.Enums.Tier
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class NewAccountScene(val app: UserInterface, val manager: AccountManager) {
    fun getScene(): Scene {
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
            try {
                if (registrationField.text.isBlank()) {
                    throw Exception("Empty Username!")
                }
                if (!manager.createAccount(registrationField.text, tierDropdown.value, phaseDropdown.value)) {
                    val alert = Alert(Alert.AlertType.ERROR, "User \"${registrationField.text}\" already exists")
                    alert.show()
                } else {
                    val message = Alert(Alert.AlertType.INFORMATION, "Created account ${registrationField.text}")
                    message.show()
                }

            } catch (e: Exception) {
                val alert = Alert(Alert.AlertType.ERROR, "No blank fields")
                alert.show()
            }
        }

        val returnButton = Button("Back")
        returnButton.setOnAction { app.setLoginScene() }

        val screen = VBox(registrationLabel, registrationField, rankLabel, rankSelector, submitButton, returnButton)

        return Scene(screen)
    }
}