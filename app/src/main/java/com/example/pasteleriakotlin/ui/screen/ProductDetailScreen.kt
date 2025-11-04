package com.example.pasteleriakotlin.ui.screen

import android.R.attr.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriakotlin.data.sampleProducts


@Composable
fun ProductDetailScreen(productId: Int, navController: NavController) {
    val product = sampleProducts.find { it.id == productId }
    var quantity by remember { mutableStateOf(1) }

    if (product != null) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // ✅ permite ver botones
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(product.name, style = MaterialTheme.typography.headlineMedium)
            Text(product.description)
            Text("Precio: $${product.price}", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Cantidad: ")
                Button(onClick = { if (quantity > 1) quantity-- }) { Text("-") }
                Text("$quantity", Modifier.padding(horizontal = 12.dp))
                Button(onClick = { quantity++ }) { Text("+") }
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) { Text("Volver") }

                Button(
                    onClick = { /* Agregar al carrito */ },
                    modifier = Modifier.weight(1f)
                ) { Text("Agregar") }

                Button(
                    onClick = { /* Comprar ahora */ },
                    modifier = Modifier.weight(1f)
                ) { Text("Comprar") }
            }
        }
    }
}
