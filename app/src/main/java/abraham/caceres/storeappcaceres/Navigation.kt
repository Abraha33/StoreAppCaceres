package abraham.caceres.storeappcaceres

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login") {
            Log.d("Navigation", "Mostrando pantalla de login")
            LoginScreen(
                onClickRegister = {
                    Log.d("Navigation", "Intentando navegar a register")
                    navController.navigate("register")
                },
                onSuccessfulLogin = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            Log.d("Navigation", "Mostrando pantalla de registro")
            RegisterScreen(
                onClickBack = {
                    Log.d("Navigation", "Intentando volver atrás")
                    navController.popBackStack()
                },
                onSuccessfulRegister = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            Log.d("Navigation", "Mostrando pantalla de inicio")
            HomeScreen()
        }
    }
}
