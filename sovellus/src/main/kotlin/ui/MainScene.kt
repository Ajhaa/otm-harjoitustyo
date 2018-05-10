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
import kotlin.math.log

class MainScene(val manager: AccountManager, val app: UserInterface) {

    fun getScene(): Scene {
        var screen = VBox()
        var welcomeText = Text("Welcome ${manager.getCurrentAccount()}\nRecord a result:")

        val radioWin = RadioButton("win")
        val radioLoss = RadioButton("loss")


/*        val dropdown1 = ComboBox<Tier>()
        dropdown1.items.addAll(Tier.values())

        val dropdown2 = ComboBox<Int>()
        dropdown2.items.addAll(1..5) */

        val radioRankUp = RadioButton("promotion")
        val radioRankNoChange = RadioButton("no change")
        val radioRankDown = RadioButton("demotion")

        var result = ToggleGroup()
        result.toggles.addAll(radioWin, radioLoss)

        val rankChange = ToggleGroup()
        rankChange.toggles.addAll(radioRankUp, radioRankNoChange, radioRankDown)

        rankChange

        val submit = Button("submit")

        submit.setOnAction {
            val resultRadio = result.selectedToggle as RadioButton
            val rankRadio = rankChange.selectedToggle as RadioButton

            val res = if (resultRadio.text == "win") Result.Win else Result.Loss

            var rank = manager.getLatestResult().rank
            if (rankRadio.text == "promotion") {
                rank = rank.increaseRank()
            } else if (rankRadio.text == "demotion") {
                rank = rank.decreaseRank()
            }

            manager.addResult(rank, res)
        }

        val toHistory = Button("match history")

        toHistory.setOnAction {
            app.setHistoryScene()
        }

        val toStatistics = Button("stats")

        toStatistics.setOnAction {
            app.setStatisticScene()
        }

        val logout = Button("log out")

        logout.setOnAction {
            manager.logout()
            app.setLoginScene()
        }

        val rankRadios = HBox(radioRankUp, radioRankNoChange, radioRankDown)
        screen.children.addAll(welcomeText, radioWin, radioLoss, rankRadios, submit, toHistory, toStatistics, logout)

        return Scene(screen)
    }
}