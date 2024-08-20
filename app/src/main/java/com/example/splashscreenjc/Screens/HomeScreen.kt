package com.example.splashscreenjc.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenjc.R
import com.example.splashscreenjc.data.Movie
import com.example.splashscreenjc.navigation.NavigationItem

@Composable
fun HomeScreen(navController: NavController) {
    MoviesColumn( movies = listOf(
        Movie.Movie(drawable = R.drawable.deadpool3, name = "Deadpool 3"),
        Movie.Movie(drawable = R.drawable.spiderman, name = "Spider-Man"),
        Movie.Movie(drawable = R.drawable.thor_love_and_thunder, name = "Thor - Love & Thunder")
    ),
        navController = navController // Set navController
    )
}

@Composable
fun MoviesColumn(
    modifier: Modifier = Modifier,
    movies: List<Movie.Movie> = emptyList(),
    navController: NavController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        modifier = modifier
    ) {
        items(movies) { category ->
            FavoriteCollectionCard(
                category.drawable,
                onClick = { // ClickListener for every FavoriteCollectionCard
                    navController
                        .navigate(
                            NavigationItem.Detail.route + "/${category.name}/${category.drawable}" // Route defined in the navHost that can receives name and drawable
                        )
                },
                name = "Deadpool 3"
            )
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }, // Empty ClickListener by default
    name: String
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .clickable(enabled = true, onClick = onClick) // Clickable element
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = name, // Cambiar "text" a "name"
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        drawable = R.drawable.deadpool3,
        name = "Deadpool 3")
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}