import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import sqDelight.DatabaseDriverFactoryImpl

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP Demo App") {
        App(DatabaseDriverFactoryImpl())
    }
}