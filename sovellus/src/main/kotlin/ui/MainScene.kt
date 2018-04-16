package ui

import domain.AccountManager
import domain.Enums.Rank
import domain.Enums.Result
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

    fun getScene(): Scene {
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

        val submit = Button("submit")

        submit.setOnAction {
            val radio = result.selectedToggle as RadioButton
            val res = if (radio.text == "win") Result.Win else Result.Loss

            val drop1 = dropdown1.value
            val drop2 = dropdown2.value

            manager.addResult(Rank(drop1, drop2), res)
        }

        val toHistory = Button("match history")

        toHistory.setOnAction {
            app.setHistoryScene()
        }

        val dropdowns = HBox(dropdown1, dropdown2)

        result.toggles.addAll(radio1, radio2)
        screen.children.addAll(welcomeText, radio1, radio2, dropdowns, submit, toHistory)

        return Scene(screen)
    }
}