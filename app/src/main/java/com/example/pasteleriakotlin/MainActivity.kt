package com.example.pasteleriakotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pasteleriakotlin.data.UsuarioDataBase
import com.example.pasteleriakotlin.ui.CatalogoPasteleria
import com.example.pasteleriakotlin.ui.screen.ProductDetailScreen
import com.example.pasteleriakotlin.ui.screen.ProductsScreen
import com.example.pasteleriakotlin.ui.screen.RegistroScreen
import com.example.pasteleriakotlin.ui.screen.SplashScreen
import com.example.pasteleriakotlin.viewmodel.UsuarioViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = UsuarioDataBase.getDatabase(this)
        val usuarioDao = database.usuarioDao()

        setContent {
            val navController = rememberNavController()

            // Crear el ViewModel directamente aquí
            val usuarioViewModel: UsuarioViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return UsuarioViewModel(usuarioDao) as T
                    }
                }
            )

            NavHost(navController, startDestination = "splash") {

                composable("splash") {
                    SplashScreen(navController = navController)
                }
                composable("registro") {
                    RegistroScreen(viewModel = usuarioViewModel, navController = navController)
                }
                composable("catalogo") {
                    CatalogoPasteleria()
                }


                composable("products") { ProductsScreen(navController) }
                composable(
                    "productDetail/{productId}",
                    arguments = listOf(navArgument("productId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("productId") ?: 0
                    ProductDetailScreen(productId = id, navController = navController)
                }
            }


        }
    }
}