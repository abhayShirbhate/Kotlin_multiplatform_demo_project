
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.DashboardScreen
import sqDelight.DatabaseDriverFactory

@Composable
@Preview
fun App(databaseDriverFactory:DatabaseDriverFactory ) {
    MaterialTheme {
        DashboardScreen(databaseDriverFactory)
    }
}