package com.dev0ko.authlib.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev0ko.authlib.R
import com.dev0ko.authlib.utils.Colors

/**
 * @param onClickBack this param for add a custom action for top back button
 */
@Composable
fun LoginScreen(onClickBack: () -> Unit) {
    Column(
        modifier = Modifier
            .background(color = Colors.BackgroundColor)
            .padding(top = 25.dp, start = 15.dp)
            .fillMaxSize()

    ) {
        Box(

            modifier = Modifier
                .clip(RoundedCornerShape(25))
                .background(color = Color.White)
                .width(50.dp)
                .height(50.dp)
                .clickable { onClickBack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = ""
            )

        }

        Text(
            text = stringResource(id = R.string.txt_login),
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = colorResource(
                id = R.color.green
            )

        )
    }

}


@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({})
}