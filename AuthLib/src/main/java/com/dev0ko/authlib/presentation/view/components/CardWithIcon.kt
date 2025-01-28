package com.dev0ko.authlib.presentation.view.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev0kch.chatbot.ui.theme.transparent
import com.dev0kch.chatbot.ui.theme.white
import com.dev0ko.authlib.R


@Composable
fun CardWithIcon(
    text: String,
    icon: Painter,
    fontSize: TextUnit,
    background: Color,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = background,
        ),
        border = BorderStroke(2.dp, colorResource(id = R.color.auth_gray)),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .clickable { onClick() }


    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier

                .height(45.dp)
                .fillMaxWidth()

                .background(transparent, shape = RoundedCornerShape(2.dp))

        ) {
            Image(
                painter = icon, contentDescription = "", modifier = Modifier.width(18.dp)


            )
            Text(
                text,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .then(textModifier),
                fontSize = fontSize,
                lineHeight = 40.sp,
                fontWeight = FontWeight.W400,
                color = colorResource(id = R.color.text_color)
            )
        }


    }

}

@Preview(uiMode =  Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CardWithIconPreview() {
    CardWithIcon(
        text = "Google",
        icon = painterResource(id = R.drawable.google_logo),
        fontSize = 15.sp,
        background = white,
        onClick = {}
    )
}