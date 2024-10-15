package com.dev0ko.authlib.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dev0kch.chatbot.ui.theme.textError
import com.dev0ko.authlib.R
import com.dev0ko.authlib.presentation.navigation.Route
import com.dev0ko.authlib.presentation.view.components.CustomLoading
import com.dev0ko.authlib.presentation.view.components.GradientButton
import com.dev0ko.authlib.presentation.viewmodel.AuthenticationViewModel
import com.dev0ko.authlib.utils.CustomAlertDialog
import com.dev0ko.authlib.utils.GlobalStyles
import com.dev0ko.authlib.utils.Resource
import com.dev0ko.authlib.utils.translate.STRINGS
import com.dev0ko.authlib.utils.translate.getAuthString
import com.dev0ko.authlib.utils.validateEmail

@Composable
fun ForgetPasswordScreen(
    navController: NavHostController?,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {

    val forgetPassword by authenticationViewModel.forgetPassword.collectAsState()


    var isEmailError by remember { mutableStateOf("") }
    var loginError by remember {
        mutableStateOf(false)
    }

    var email by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }
    var isSentLink by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background))

            .padding(
                top = 25.dp,
                start = GlobalStyles.Padding.ScreenPadding,
                end = GlobalStyles.Padding.ScreenPadding
            )
            .fillMaxWidth()
            .fillMaxHeight()


    ) {
        Box(

            modifier = Modifier
                .clip(RoundedCornerShape(25))
                .background(color = Color.White)
                .width(50.dp)
                .height(50.dp)
                .clickable {
                    navController?.navigate(Route.LoginScreen.route)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = ""
            )

        }


        LaunchedEffect(forgetPassword) {

            when (forgetPassword) {
                is Resource.Failure -> {
                    // Handle failure
                    loginError = true
                    loading = false
                }

                is Resource.Loading -> {
                    // Show loading
                    loading = true
                }

                is Resource.Success -> {
                    //        Handle success
                    loading = false
                    isSentLink = true
                }

                else -> {}
            }
        }


        val listColors =
            listOf(colorResource(id = R.color.auth_green), colorResource(id = R.color.auth_green))





        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(top = 15.dp)
        ) {

            if (loading) {

                CustomLoading()

            }
            if (isSentLink) {
                CustomAlertDialog(
                    onDismissRequest = { isSentLink = false },
                    dismissText = "",
                    onConfirmation = { isSentLink = false },
                    confirmationText = stringResource(id = R.string.txt_ok),
                    dialogTitle = stringResource(id = R.string.dialog_msg_check_email),
                    dialogText = "${stringResource(id = R.string.dialog_msg_check_email_deescription)} $email",

                    )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.security_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(70.dp)
                        .padding(top = 10.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            Text(
                text = stringResource(id = R.string.txt_reset_passowrd),
                modifier = Modifier.padding(
                    top = GlobalStyles.Padding.ScreenPadding,
                    bottom = 10.dp
                ),
                color = colorResource(id = R.color.title_one_color),
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )



            Text(
                text = stringResource(id = R.string.txt_email),
                color = colorResource(id = R.color.title_one_color),
                modifier = Modifier.padding(
                    top = GlobalStyles.Padding.ScreenPadding,

                    ), fontSize = 13.sp

            )
            TextField(

                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.txt_enter_email),
                        fontSize = 13.sp
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()

                    .border(
                        shape = RoundedCornerShape(10),
                        border = BorderStroke(1.dp, color = colorResource(id = R.color.white))
                    ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.background_text_filed), // To avoid default background
                    unfocusedContainerColor = colorResource(id = R.color.background_text_filed),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent

                )
            )

            Text(
                text = isEmailError,
                color = textError,
                fontSize = GlobalStyles.Text.H5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 2.dp,
                        bottom = 3.dp,

                        ),
            )



            GradientButton(
                gradientColors = listColors,
                cornerRadius = 5.dp, nameButton = stringResource(id = R.string.txt_reset),
                roundedCornerShape = RoundedCornerShape(5.dp),
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    if (!validateEmail(email)){
                        isEmailError = getAuthString(STRINGS.ENTER_VALID_EMAIL)

                    }
                else{
                        if (isEmailError.isEmpty()) {
                            email = email.replace(" ", "")
                            authenticationViewModel.forgetPassword(email)
                        }
                    }

                }
            )


        }

    }


}








