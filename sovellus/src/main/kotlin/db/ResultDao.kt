package db

import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import domain.GameResult
import java.sql.Connection

/**
 * used to access the result table of the dataase
 */

class ResultDao(val connection: Connection) {
    /**
     * find result linked to an account
     * @param id id of the target account
     */

    fun getByAccountId(id: Int): ArrayList<GameResult> {
        val results = ArrayList<GameResult>()

        val statement = connection.prepareStatement("SELECT * FROM Result WHERE account_id = ?")
        statement.setInt(1, id)

        val result = statement.executeQuery()
        while (result.next()) {
            val rank = Rank(Tier.valueOf(result.getString("tier")), result.getInt("phase"))
            val outcome = Result.valueOf(result.getString("result"))
            val champion = Champion.valueOf(result.getString(("champion")))

            results.add(GameResult(rank, outcome, champion))
        }

        return results
    }

    /**
     * adds a new result for an account
     * @param result the result object to be added
     * @param accId id of the target account
     */

    fun add(result: GameResult, accId: Int) {
        val statement = connection.prepareStatement("INSERT INTO Result (account_id, tier, phase, result, champion) values(?,?,?,?,?)")
        statement.setInt(1, accId)
        statement.setString(2, result.rank.tier.toString())
        statement.setInt(3, result.rank.phase)
        statement.setString(4, result.result.toString())
        statement.setString(5, result.champion.toString())
        statement.execute()
    }
}