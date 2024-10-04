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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dev0kch.chatbot.presentation.viewmodel.AuthenticationViewModel
import com.dev0kch.chatbot.ui.theme.textColorHint
import com.dev0kch.chatbot.ui.theme.textError
import com.dev0ko.authlib.R
import com.dev0ko.authlib.presentation.view.components.CustomLoading
import com.dev0ko.authlib.presentation.view.components.GradientButton
import com.dev0ko.authlib.utils.CustomAlertDialog
import com.dev0ko.authlib.utils.GlobalStyles
import com.dev0ko.authlib.utils.Resource
import com.dev0ko.authlib.utils.translate.STRINGS
import com.dev0ko.authlib.utils.translate.getAuthString
import com.dev0ko.authlib.utils.validateEmail

/**
 * @param onClickBack this param for add a custom action for top back button
 */
@Composable
fun LoginScreen(
    onClickBack: () -> Unit,
    navController: NavHostController?,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {

    var isEmailError by remember { mutableStateOf("") }
    var isPasswordError by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }
    val loginFlow by authenticationViewModel.loginFLow.collectAsState()


    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background))
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


        LaunchedEffect(loginFlow) {

            when (loginFlow) {
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
                    // navController?.navigate(Route.MainDrawer.route)
                    loading = false
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
                .padding(top = 20.dp)
        ) {

            if (loading) {

                CustomLoading()

            }
            if (loginError) {
                CustomAlertDialog(
                    onDismissRequest = { loginError = false },
                    dismissText = "",
                    onConfirmation = { loginError = false },
                    confirmationText = stringResource(id = R.string.txt_ok),
                    dialogTitle = stringResource(id = R.string.dialog_msg_opes),
                    dialogText = stringResource(id = R.string.dialog_invalid_login),
                    icon = Icons.Default.Info,

                    )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
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
                text = stringResource(id = R.string.txt_login_into_account),
                modifier = Modifier.padding(
                    top = GlobalStyles.Padding.ScreenPadding,
                    start = GlobalStyles.Padding.ScreenPadding,
                    end = GlobalStyles.Padding.ScreenPadding,
                    bottom = 10.dp
                ),
                color = colorResource(id = R.color.title_one_color),
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )

            Text(
                text = stringResource(id = R.string.txt_wlcm_back),
                modifier = Modifier.padding(
                    start = GlobalStyles.Padding.ScreenPadding,
                ),
                color = textColorHint,
                fontSize = 13.sp,
                fontWeight = FontWeight.W400
            )



            Text(
                text = stringResource(id = R.string.txt_email),
                color = colorResource(id = R.color.title_one_color),
                modifier = Modifier.padding(
                    top = GlobalStyles.Padding.ScreenPadding,
                    start = GlobalStyles.Padding.ScreenPadding,
                    end = GlobalStyles.Padding.ScreenPadding
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
                    .padding(
                        start = GlobalStyles.Padding.ScreenPadding,
                        end = GlobalStyles.Padding.ScreenPadding
                    )
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
                        bottom = 5.dp,
                        start = GlobalStyles.Padding.ScreenPadding,
                        end = GlobalStyles.Padding.ScreenPadding
                    ),
            )

            Text(
                text = stringResource(id = R.string.txt_password),
                fontSize = 13.sp,
                color = colorResource(id = R.color.title_one_color),
                modifier = Modifier.padding(
                    top = 10.dp,
                    start = GlobalStyles.Padding.ScreenPadding,
                    end = GlobalStyles.Padding.ScreenPadding
                ),
            )
            TextField(

                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.txt_enter_password),
                        fontSize = 13.sp
                    )
                },

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.background_text_filed), // To avoid default background
                    unfocusedContainerColor = colorResource(id = R.color.background_text_filed),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = GlobalStyles.Padding.ScreenPadding,
                        end = GlobalStyles.Padding.ScreenPadding
                    )
                    .border(
                        shape = RoundedCornerShape(10),
                        border = BorderStroke(1.dp, color = colorResource(id = R.color.white))
                    ),

                )

            Text(
                text = isPasswordError,
                color = textError,
                fontSize = GlobalStyles.Text.H5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 2.dp,
                        start = GlobalStyles.Padding.ScreenPadding,
                        end = GlobalStyles.Padding.ScreenPadding
                    ),
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = GlobalStyles.Padding.ScreenPadding
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.txt_forget_password),
                    color = textColorHint,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W700
                )
            }

            GradientButton(
                gradientColors = listColors,
                cornerRadius = 5.dp, nameButton = stringResource(id = R.string.txt_login),
                roundedCornerShape = RoundedCornerShape(5.dp),
                modifier = Modifier.padding(top = 40.dp),
                onClick = {
                    val errors = validateCredentials(email, password)
                    isEmailError = errors.first
                    isPasswordError = errors.second

                    if (isEmailError.isEmpty() && isPasswordError.isEmpty()) {
                        authenticationViewModel.login(email, password)
                    }
                }
            )
        }

    }


}


fun validateCredentials(email: String, password: String): Pair<String, String> {
    var emailError = ""
    var passwordError = ""


    if (email.isEmpty()) {
            emailError = getAuthString(STRINGS.EMAIL_IS_REQUIRED)
    }



    if (password.isEmpty()) {
        passwordError = getAuthString(STRINGS.PASSWORD_IS_REQUIRED)

    }
    if (password.length <= 6 && !password.isEmpty()) {
        passwordError = getAuthString(STRINGS.PASSWORD_MUST_BE_LEAST_6CH)

    }
    if (!validateEmail(email) && !email.isEmpty()) {
        emailError = getAuthString(STRINGS.ENTER_VALID_EMAIL)

    }


    return Pair(emailError, passwordError)
}


@Preview
@Composable
fun LoginScreenPreview(){
        LoginScreen(onClickBack = { /*TODO*/ }, navController = null, )

}





