package ui

import domain.AccountManager
import domain.StatisticsGenerator
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text

class Statistics(val manager: AccountManager, val app: UserInterface) {

    fun getScene(): Scene {
        val statistics = StatisticsGenerator(manager)
        val topText = Text("Statistics:")

        if (statistics.winRateHistory().isEmpty()) {
            throw Exception("Record some matches first!")
        }

        val winPercentage = "%.2f".format(statistics.getWinRate()*100)

        val winRateText = Text("Win rate: $winPercentage%")
        winRateText.fill = Color.BLUE
        winRateText.isUnderline = true
        winRateText.style = "-fx-cursor: hand"

        winRateText.setOnMouseClicked { app.setWinrateHistoryScene() }

        val toRankStats = Button("Rank statistics")
        toRankStats.setOnAction { app.setRankStatisticScene() }

        val toChampionStats = Button("Champion statistics")
        toChampionStats.setOnAction { app.setChampionStatisticScene() }

        val backButton = Button("Back")
        backButton.setOnAction { app.setMainScene() }

        val screen = VBox(5.0, topText, winRateText, toRankStats, toChampionStats, backButton)

        return Scene(screen)
    }
}