import androidx.compose.ui.window.ComposeUIViewController
import sqDelight.DatabaseDriverFactoryImpl

fun MainViewController() = ComposeUIViewController { App(DatabaseDriverFactoryImpl()) }