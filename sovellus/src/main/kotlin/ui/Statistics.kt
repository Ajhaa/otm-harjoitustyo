package ui

import domain.AccountManager
import domain.StatisticsGenerator
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class Statistics(val manager: AccountManager, val app: UserInterface) {

    fun getScene() : Scene {
        val statistics = StatisticsGenerator(manager)
        val topText = Text("Statistics:")

        val winPercentage = "%.2f".format(statistics.getWinRate()*100)

        val winRateText = Text("Win rate: $winPercentage%")

        winRateText.setOnMouseClicked { app.setWinrateHistoryScene() }

        val toRankStats = Button("Rank statistics")
        toRankStats.setOnAction { app.setRankStatisticScene() }

        val toChampionStats = Button("Champion statistics")
        toChampionStats.setOnAction { app.setChampionStatisticScene() }

        val backButton = Button("Back")
        backButton.setOnAction { app.setMainScene() }

        val screen = VBox(topText, winRateText, toRankStats, toChampionStats, backButton)

        return Scene(screen)
    }
}