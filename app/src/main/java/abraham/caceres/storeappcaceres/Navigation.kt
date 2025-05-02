package abraham.caceres.storeappcaceres

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val myNavController = rememberNavController()
    val myStartDestination: String = "login"
    NavHost(
        navController = myNavController,
        startDestination = myStartDestination
    ){
        composable("login"){
            LoginScreen(onClickRegister = {
                myNavController.navigate("register")
            }, onSuccessfulLogin = {
                myNavController.navigate("home"){
                    popUpTo("Login"){inclusive = true}
                }
            })
        }
        composable("register"){
            RegisterScreen(onClickBack={
                myNavController.popBackStack()
            },
                onSuccessfulRegister = {
                    myNavController.navigate("home"){
                        popUpTo(0)
                    }
                })
        }

    }
}