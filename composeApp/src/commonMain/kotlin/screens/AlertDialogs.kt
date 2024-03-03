package screens

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogWithSingleButton(
    title: String,
    description: String,
    positiveButtonText: String,
    onPositiveButtonClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { /* Handle dialog dismiss if needed */ },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            Button(
                onClick = {
                    onPositiveButtonClick()
                }
            ) {
                Text(text = positiveButtonText)
            }
        }

    )
}