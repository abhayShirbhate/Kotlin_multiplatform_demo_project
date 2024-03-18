package sqDelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.mounty.camper.cache.MyAppDb
import java.io.File

class DatabaseDriverFactoryImpl: DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        val databasePath = File(System.getProperty("java.io.tmpdir"), "user.db")
        val driver: SqlDriver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
        MyAppDb.Schema.create(driver)
        return driver
    }
}