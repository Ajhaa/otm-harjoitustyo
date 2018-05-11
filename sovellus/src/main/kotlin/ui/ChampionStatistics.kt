package ui

import domain.AccountManager
import domain.Enums.Champion
import domain.StatisticsGenerator
import javafx.scene.Scene
import javafx.scene.chart.BarChart
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import java.util.*

class ChampionStatistics(val manager: AccountManager, val app: UserInterface) {

    fun getScene() : Scene {
        val statistics = StatisticsGenerator(manager)

        val xAxis = CategoryAxis()
        val yAxis = NumberAxis()
        val chart: BarChart<String, Number> = BarChart(xAxis, yAxis)
        chart.title = "Champion statistics"
        chart.isLegendVisible = false


        val championStats = statistics.getChampionRates()

        val series: XYChart.Series<String, Number> = XYChart.Series()
        for (e in championStats) {
            series.data.add(XYChart.Data(e.key.toString(), e.value))
        }

        chart.data.add(series)
        yAxis.tickUnit = 1.0

        val returnButton = Button("back")
        returnButton.setOnAction { app.setStatisticScene() }

        return Scene(VBox(chart, returnButton))


    }
}