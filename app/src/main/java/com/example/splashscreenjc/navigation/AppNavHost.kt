package com.example.splashscreenjc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.splashscreenjc.R
import com.example.splashscreenjc.Screens.DetailScreen
import com.example.splashscreenjc.Screens.HomeScreen
import com.example.splashscreenjc.Screens.LoginScreen
import com.example.splashscreenjc.data.Movie

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier, // Modificador para aplicar al diseño
    navController: NavHostController, // Controlador de navegación
    startDestination: String = NavigationItem.Login.route // Ruta inicial
) {
    NavHost( // Provee un lugar en la jerarquía de Compose para manejar la navegación
        modifier = modifier,
        navController =  navController,
        startDestination = startDestination
    ) {
        composable( // Añade una pantalla composable al NavGraphBuilder
            route = NavigationItem.Login.route // Ruta para la pantalla de inicio de sesión
        ) {
            LoginScreen(navController) // Muestra LoginScreen
        }

        composable( // Añade una pantalla composable al NavGraphBuilder
            route = NavigationItem.Home.route // Ruta para la pantalla de inicio
        ) {
            HomeScreen(navController) // Muestra HomeScreen
        }

        composable( // Añade una pantalla composable al NavGraphBuilder
            route = NavigationItem.Detail.route + "/{movieName}/{movieImage}", // Ruta con argumentos
            arguments = listOf( // Define los argumentos esperados por la ruta
                navArgument(name = "movieName") {
                    type = NavType.StringType
                    defaultValue = "" // Valor por defecto si no se proporciona
                },
                navArgument(name = "movieImage") {
                    type = NavType.IntType
                    defaultValue = R.drawable.no_image_available // Imagen por defecto
                }
            )
        ) {
            val image = it.arguments?.getInt("movieImage") ?: R.drawable.no_image_available // Obtiene la imagen
            val name = it.arguments?.getString("movieName") ?: "" // Obtiene el nombre de la película

            // Crea un nuevo objeto Movie
            val movie = Movie.Movie(image, name)

            // Muestra DetailScreen pasando el objeto movie
            DetailScreen(movie = movie, navController = navController)
        }
    }
}


