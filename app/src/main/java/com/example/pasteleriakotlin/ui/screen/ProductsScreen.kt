package com.example.pasteleriakotlin.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriakotlin.data.sampleProducts
import com.example.pasteleriakotlin.model.Product

@Composable
fun ProductsScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("all") }
    val categories = listOf("all") + sampleProducts.map { it.category }.distinct()

    val filteredProducts = if (selectedCategory == "all") {
        sampleProducts
    } else {
        sampleProducts.filter { it.category == selectedCategory }
    }

    Column(Modifier.padding(16.dp)) {
        Text("Nuestros Productos", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        // Filtro de categorías
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Categoría: ", fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(8.dp))
            DropdownMenuCategory(
                categories = categories,
                selected = selectedCategory,
                onSelect = { selectedCategory = it }
            )
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(filteredProducts) { product ->
                ProductCard(product = product) {
                    navController.navigate("productDetail/${product.id}")
                }
            }
        }
    }
}

@Composable
fun DropdownMenuCategory(categories: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(if (selected == "all") "Todos los productos" else selected)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            categories.forEach { cat ->
                DropdownMenuItem(
                    text = { Text(if (cat == "all") "Todos los productos" else cat) },
                    onClick = {
                        onSelect(cat)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() } // 👈 aquí va el clic
    ) {
        Row(Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text(product.description)
                Text("Precio: $${product.price}")
            }
        }
    }
}


