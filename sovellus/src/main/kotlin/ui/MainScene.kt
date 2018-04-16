package ui

import domain.AccountManager
import domain.Enums.Tier
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class MainScene(val manager: AccountManager, val app: UserInterface) {

    fun getScene() : Scene {
        var screen = VBox()
        var welcomeText = Text("Welcome ${manager.getCurrentAccount()}\nRecord a result:")

        val radio1 = RadioButton("win")
        val radio2 = RadioButton("loss")

        val dropdown1 = ComboBox<Tier>()
        dropdown1.items
                .addAll(Tier.values())

        val dropdown2 = ComboBox<Int>()
        dropdown2.items.addAll(1..5)

        var result = ToggleGroup()

        val button = Button("submit")

        button.setOnAction {
            val radio = result.selectedToggle as RadioButton
            val drop1 = dropdown1.value
            val drop2 = dropdown2.value
            println(radio.text + " " + drop1 + " " + drop2)
        }

        val dropdowns = HBox(dropdown1, dropdown2)

        result.toggles.addAll(radio1,radio2)
        screen.children.addAll(welcomeText, radio1, radio2, dropdowns, button)

        return Scene(screen)

    }
}