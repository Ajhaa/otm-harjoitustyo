package ui

import domain.AccountManager
import domain.StatisticsGenerator
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class WinrateHistory(val manager: AccountManager, val app: UserInterface) {

    fun getScene(): Scene {
        val stats = StatisticsGenerator(manager).winRateHistory()
        val yAxis = NumberAxis(0.0, 100.0, 1.0)
        val xAxis = NumberAxis(1.0, stats.size.toDouble(), 1.0)

        val chart = LineChart(xAxis, yAxis)
        chart.isLegendVisible = false
        chart.title = "Winrate development"

        val series: XYChart.Series<Number, Number> = XYChart.Series()

        for (s in stats) {
            series.data.add(XYChart.Data(s.key, s.value*100))
        }
        chart.data.add(series)

        val returnButton = Button("back")
        returnButton.setOnAction { app.setStatisticScene() }

        val scene = Scene(VBox(5.0, chart, returnButton))

        return scene
    }
}