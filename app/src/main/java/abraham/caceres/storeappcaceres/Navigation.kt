package abraham.caceres.storeappcaceres

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val myNavHostController = rememberNavController()
    val myStartDestination: String = "login"
    NavHost(
        navController = myNavHostController,
        startDestination = myStartDestination
    ){
        composable("login"){
            LoginScreen(onClickRegister = {
                myNavHostController.navigate("register")
            })
        }
        composable("register"){
            RegisterScreen(onClickBack={
                myNavHostController.navigate("login")
            })
        }

    }
}