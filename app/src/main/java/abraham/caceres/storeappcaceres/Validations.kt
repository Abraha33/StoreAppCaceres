package abraham.caceres.storeappcaceres

import android.util.Patterns
import androidx.core.text.isDigitsOnly
import androidx.credentials.PasswordCredential
import javax.inject.Named

//Returnar true si es valido y false si es invalido
fun validationEmail(email: String): Pair<Boolean,String>{
    return when{
        email.isEmpty() -> Pair(false, "correo esta vacio ")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "el correo no registrado")
        !email.endsWith("@unab.edu.co") && !email.endsWith("@gmail.com") && !email.endsWith("@test.com") -> 
            Pair(false, "el correo debe ser @unab.edu.co, @gmail.com o @test.com")
        else -> Pair(true, "")
    }
}

fun validatePassword(password: String):Pair<Boolean, String>{
    return when {
        password.isEmpty() -> Pair(false, "contrasena no ingresada")
        password.length < 8 -> Pair(false, "la contrasena debe tener al menos 8 caracteres")
        !password.any { it.isDigit() } -> Pair(false, "la contrasena debe tener al menos un numero")
        else -> Pair(true, "")
    }
}

fun validateName (name: String): Pair<Boolean, String>{
    return when{
        name.isEmpty() -> Pair(false, "nombre no ingresada")
        name.length < 3 -> Pair(false, "nombre debe tener al menos 3 caracteres")
        else -> Pair(true, "")
    }
}

fun validateConfirmPassword(password: String, confirmPassword:String):Pair<Boolean, String>{
    return when {
        confirmPassword.isEmpty() -> Pair(false, "la contrasena es requerida")
        confirmPassword!= password -> Pair(false, "la contrasena no coincide")              
        else -> Pair(true, "")
    }
}