package db

import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import domain.GameResult
import java.sql.Connection

class ResultDao(val connection: Connection) {

    fun getByAccountId(id: Int) : ArrayList<GameResult> {
        val results = ArrayList<GameResult>()

        val statement = connection.prepareStatement("SELECT * FROM Result WHERE account_id = ?")
        statement.setInt(1, id)

        val result = statement.executeQuery()
        while (result.next()) {
            val rank = Rank(Tier.valueOf(result.getString("tier")), result.getInt("phase"))
            results.add(GameResult(rank, Result.valueOf(result.getString("result"))))
        }

        return results
    }

    fun add(result: GameResult, accId: Int) {
        val statement = connection.prepareStatement("INSERT INTO Result (account_id, tier, phase, result) values(?,?,?,?)")
        statement.setInt(1, accId)
        statement.setString(2, result.rank.tier.toString())
        statement.setInt(3, result.rank.phase)
        statement.setString(4, result.result.toString())
        statement.execute()
    }
}