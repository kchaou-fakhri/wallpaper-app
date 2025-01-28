package com.dev0ko.authlib.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    dismissText : String?,
    onConfirmation: () -> Unit,
    confirmationText: String?,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector?  = null,
) {
    AlertDialog(
        icon = {
            if (icon != null) {
                Icon(icon, contentDescription = "")
            }
        },
        title = {
            Text(text = dialogTitle, fontSize = 15.sp, fontWeight = FontWeight.W700)
        },
        text = {
            Text(text = dialogText, fontSize = 13.sp)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmationText?: "")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissText?: "")
            }
        }
    )
}