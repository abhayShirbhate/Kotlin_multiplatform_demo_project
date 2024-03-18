package org.abhay.project.sqDelight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mounty.camper.cache.MyAppDb
import sqDelight.DatabaseDriverFactory

class DatabaseDriverFactoryImpl(private val context: Context):DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MyAppDb.Schema,context,"user.db")
    }

}