package com.example.lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazylist.ui.theme.LazyListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyList(perfiles = listaPerfiles)
                }
            }
        }
    }
}

// Lista con scroll: igual que la de Lunes a Domingo, pero con perfiles
@Composable
fun LazyList(perfiles: List<Perfil>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Perfiles",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        items(items = perfiles, key = { it.id }) { perfil ->
            TarjetaMeGusta(perfil = perfil)
        }
    }
}

@Composable
fun TarjetaMeGusta(perfil: Perfil, modifier: Modifier = Modifier) {
    // Contador de "me gusta" de cada perfil
    var meGusta by rememberSaveable { mutableIntStateOf(0) }

    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Foto de perfil vacía (por el momento solo un círculo gris)
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = perfil.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = perfil.carrera,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón de me gusta con su contador
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { meGusta++ }) {
                    Text("Me gusta")
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = if (meGusta > 0) "❤️" else "🤍")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$meGusta Me gusta")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyListTheme {
        LazyList(perfiles = listaPerfiles)
    }
}