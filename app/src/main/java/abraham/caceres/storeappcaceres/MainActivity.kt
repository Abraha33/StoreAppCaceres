package abraham.caceres.storeappcaceres

import abraham.caceres.storeappcaceres.ui.theme.StoreAppCaceresTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoreAppCaceresTheme {
                Navigation()
            }
        }
    }
}
