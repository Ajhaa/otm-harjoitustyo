package ui

import domain.AccountManager
import domain.Enums.Champion
import domain.Enums.Result
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class MainScene(val manager: AccountManager, val app: UserInterface) {

    fun getScene(): Scene {
        var screen = VBox(5.0)
        var welcomeText = Text("Welcome ${manager.getCurrentAccount()}\n" +
                "Your current rank is: ${manager.getLatestResult().rank}\n" +
                "Record a result:")

        val radioWin = RadioButton("win")
        val radioLoss = RadioButton("loss")

        val championLabel = Label("Champion:")

        val playedChampion = ComboBox<Champion>()
        playedChampion.items.addAll(Champion.values())

        val radioRankUp = RadioButton("promotion")
        val radioRankNoChange = RadioButton("no change")
        val radioRankDown = RadioButton("demotion")

        var result = ToggleGroup()
        result.toggles.addAll(radioWin, radioLoss)

        val rankChange = ToggleGroup()
        rankChange.toggles.addAll(radioRankUp, radioRankNoChange, radioRankDown)

        val submit = Button("submit")

        submit.setOnAction {
            try {
                val resultRadio = result.selectedToggle as RadioButton
                val rankRadio = rankChange.selectedToggle as RadioButton

                val res = if (resultRadio.text == "win") Result.Win else Result.Loss

                var rank = manager.getLatestResult().rank
                if (rankRadio.text == "promotion") {
                    rank = rank.increaseRank()
                } else if (rankRadio.text == "demotion") {
                    rank = rank.decreaseRank()
                }

                manager.addResult(rank, res, playedChampion.value)
            } catch (e: Exception) {
                val alert = Alert(Alert.AlertType.ERROR, "Please fill everything before submitting")
                alert.show()
            }
        }

        val toHistory = Button("match history")

        toHistory.setOnAction {
            app.setHistoryScene()
        }

        val toStatistics = Button("stats")

        toStatistics.setOnAction {
            try {
                app.setStatisticScene()
            } catch (e: Exception) {
                val message = Alert(Alert.AlertType.INFORMATION, "No statistics to show yet, record some matches first!")
                message.show()
            }
        }

        val logout = Button("log out")

        logout.setOnAction {
            manager.logout()
            app.setLoginScene()
        }

        val rankRadios = HBox(radioRankUp, radioRankNoChange, radioRankDown)
        screen.children.addAll(welcomeText, radioWin, radioLoss, championLabel, playedChampion, rankRadios, submit, toHistory, toStatistics, logout)

        return Scene(screen)
    }
}