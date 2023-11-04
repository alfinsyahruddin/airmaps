package dev.alfin.airmaps.modules.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dev.alfin.airmaps.modules.presentation.navigation.AppNavHost
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            AppTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}
