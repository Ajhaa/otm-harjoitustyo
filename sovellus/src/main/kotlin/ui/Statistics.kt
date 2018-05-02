package ui

import domain.AccountManager
import domain.StatisticsGenerator
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox

class Statistics(var manager: AccountManager, val app: UserInterface) {

    fun getScene(): Scene {
        val stats = StatisticsGenerator(manager)

        val results = stats.getData()

        var xAxis = NumberAxis(1.0, results.size.toDouble(), 1.0)
        var yAxis = NumberAxis(1.0, stats.biggestRank, 1.0)

        xAxis.label = "games"
        yAxis.label = "rank"

        var chart: LineChart<Number, Number> = LineChart(xAxis, yAxis)
        chart.title = "Rank development"

        var data: XYChart.Series<Number, Number> = XYChart.Series()

        for (r in results) {
            data.data.add(XYChart.Data(r.key, r.value))
        }

        chart.data.add(data)

        val returnButton = Button("return")
        returnButton.setOnAction { app.setMainScene() }

        var screen = VBox(chart, returnButton)
        val scene = Scene(screen)

        return scene
    }
}