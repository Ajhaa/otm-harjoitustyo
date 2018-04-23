package ui

import domain.AccountManager
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart

class Statistics(var manager: AccountManager) {

    fun getScene() : Scene {
        var results = manager.getCurrentAccount()!!.results
        val biggestRank = manager.getHighestRankValue().toDouble()

        var xAxis = NumberAxis(1.0, results.size.toDouble(), 1.0)
        var yAxis = NumberAxis(1.0, biggestRank, 1.0)
        xAxis.label = "games"
        yAxis.label = "rank"



        var chart: LineChart<Number, Number> = LineChart(xAxis, yAxis)
        chart.title = "Rank development"

        var data: XYChart.Series<Number, Number> = XYChart.Series()

        var i = 1
        for (r in results) {
            data.data.add(XYChart.Data(i, r.rank.getRankAsNumber()))
            i++
        }

        chart.data.add(data)

        return Scene(chart)

    }
}