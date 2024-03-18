package sqDelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mounty.camper.cache.MyAppDb

class DatabaseDriverFactoryImpl:DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MyAppDb.Schema,"user.db")
    }
}