package ui

import domain.AccountManager
import domain.StatisticsGenerator
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart

class WinrateHistory(val manager: AccountManager, val app: UserInterface) {

    fun getScene(): Scene {
        val stats = StatisticsGenerator(manager).winRateHistory()
        println(stats)
        val yAxis = NumberAxis(0.0, 100.0, 1.0)
        val xAxis = NumberAxis()

        val chart = LineChart(xAxis, yAxis)
        chart.isLegendVisible = false

        val series: XYChart.Series<Number, Number> = XYChart.Series()

        for (s in stats) {
            series.data.add(XYChart.Data(s.key, s.value))
        }
        chart.data.add(series)

        return Scene(chart)
    }
}