package abraham.caceres.storeappcaceres

import android.R.attr.navigationIcon
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegisterScreen(onClickBack: () -> Unit = {}, onSuccessfulRegister : () -> Unit = {}) {

    //auth
    val auth = Firebase.auth
    val activity = LocalView.current.context as Activity

    var inputEmail by remember{ mutableStateOf("") }
    var inputPassword by remember{ mutableStateOf("") }
    var inputName by remember { mutableStateOf("") }
    var inputPasswordConfirmation by remember { mutableStateOf("") }


    var nameError by remember {mutableStateOf("")}
    var emailError by remember {mutableStateOf("")}
    var passwordError by remember {mutableStateOf("")}
    var passwordConfirmaError by remember {mutableStateOf("")}
    var registerError by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title ={
                },
                navigationIcon = {
                    IconButton(onClick = onClickBack){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Icon login",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "Registrar Usuario", fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFFF9900)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputName,
                onValueChange = {inputName = it},
                label = { Text("Nombre") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "icon register"
                    )
                },
                supportingText = {
                    if (nameError.isNotEmpty()){
                        Text(
                            text = nameError,
                            color = Color.Red)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputEmail,
                onValueChange = {inputEmail = it},
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email icon"
                    )
                },
                supportingText = {
                    if (emailError.isNotEmpty()){
                        Text(
                            text = emailError,
                            color = Color.Red)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputPassword,
                onValueChange = {inputPassword = it},
                label = { Text("Contraseña") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )
                },
                supportingText = {
                    if (passwordError.isNotEmpty()){
                        Text(
                            text = passwordError,
                            color = Color.Red)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputPasswordConfirmation,
                onValueChange = {inputPasswordConfirmation= it},
                label = { Text("Confirmar contraseña") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "confirm password Icon"
                    )
                },
                supportingText = {
                    if (passwordConfirmaError.isNotEmpty()){
                        Text(
                            text = passwordConfirmaError,
                            color = Color.Red)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val isValidateName =validateName(inputName).first
                val isValidateEmail =validationEmail(inputEmail).first
                val isValidatePassword =validatePassword(inputPassword).first
                val isValidateConfirmPassword =
                    validateConfirmPassword(inputPassword, inputPasswordConfirmation).first
                nameError = validateName(inputName).second
                emailError = validationEmail(inputEmail).second
                passwordError = validatePassword(inputPassword).second
                passwordConfirmaError = validateConfirmPassword(inputPassword, inputPasswordConfirmation).second

                if (isValidateName && isValidateEmail && isValidatePassword && isValidateConfirmPassword) {
                    auth.createUserWithEmailAndPassword(inputEmail, inputPassword).
                        addOnCompleteListener(activity) { task ->
                            if (task.isSuccessful) {
                                onSuccessfulRegister()
                            } else {
                                val exception = task.exception
                                registerError = when (exception) {
                                    is FirebaseAuthInvalidCredentialsException -> "Correo o contraseña incorrecta"
                                    is FirebaseAuthInvalidUserException -> "No existe una cuenta con ese correo"
                                    else -> "Error al iniciar sesión. Intenta de nuevo"
                                }
                            }
                        }
                } else {
                    registerError = "Error al registrarse"
                }


            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9900),
                    contentColor = Color.White
                )
            ){
                Text(text = "Registrarse")
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = onClickBack){
                Text("Ya tienes cuenta? Inicia Sesión",
                    color = Color(0xFFFF9900)
                )
            }
        }
    }
}