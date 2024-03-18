package org.abhay.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.abhay.project.sqDelight.DatabaseDriverFactoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(DatabaseDriverFactoryImpl(this))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
}