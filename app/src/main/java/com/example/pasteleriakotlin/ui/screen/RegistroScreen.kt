package com.example.pasteleriakotlin.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriakotlin.R
import com.example.pasteleriakotlin.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(viewModel: UsuarioViewModel, navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var contrasenaVisible by rememberSaveable  { mutableStateOf(false) }

    val usuarios by viewModel.usuarios.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        color = Color(0xFFF6F0DE)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(
                text = "Registro de Usuario",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFAD812C)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()



            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = if (contrasenaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { contrasenaVisible = !contrasenaVisible }) {
                        Icon(
                            imageVector = if (contrasenaVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank() && contrasena.isNotBlank()) {
                        viewModel.insertarUsuario(nombre, contrasena)
                        nombre = ""
                        contrasena = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6E3F2F)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Registrar Usuario", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFF5EAD3)) // color rosa pastel
                    .padding(16.dp) // el padding va después del fondo
                    .align(Alignment.CenterHorizontally)
                    .animateContentSize()
            ) {
                Text(
                    text = "Usuarios guardados:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            usuarios.forEach { usuario ->
                Text("• ${usuario.nombre}")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("catalogo") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDAA541)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Ver Catálogo de Productos", color = Color.White)
            }
            Spacer(modifier = Modifier.height(52.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFF5EAD3)) // color rosa pastel
                    .padding(30.dp)
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription = "Logo",
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}