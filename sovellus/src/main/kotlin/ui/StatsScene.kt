package ui

import domain.AccountManager
import domain.Statistics
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.Button
import javafx.scene.layout.VBox

class StatsScene(var statistics: Statistics, val app: UserInterface) {

    fun getScene() : Scene {
        var statsData = statistics.getData()
        var biggestRank = statistics.biggestRank

        val returnButton = Button("return")

        returnButton.setOnAction { app.setMainScene() }

        if (statsData.isEmpty()) {
            return Scene(VBox(returnButton), 256.0, 144.0)
        }

        var xAxis = NumberAxis(1.0, statsData.size.toDouble(), 1.0)
        var yAxis = NumberAxis(1.0, biggestRank!!, 1.0)
        xAxis.label = "games"
        yAxis.label = "rank"

        var chart: LineChart<Number, Number> = LineChart(xAxis, yAxis)
        chart.title = "Rank development"

        var data: XYChart.Series<Number, Number> = XYChart.Series()

        for (entry in statsData.entries) {
            data.data.add(XYChart.Data(entry.key, entry.value))
        }

        chart.data.add(data)

        return Scene(VBox(chart, returnButton))

    }
}