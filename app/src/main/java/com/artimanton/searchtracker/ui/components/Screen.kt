package com.artimanton.searchtracker.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artimanton.searchtracker.data.db.RequestEntity
import com.artimanton.searchtracker.viewmodel.RequestViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryListScreen(viewModel: RequestViewModel = viewModel()) {
    val entity by viewModel.queries.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Browser Queries") })
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                items(entity) { entity ->
                    QueryItem(entity = entity, onDelete = { viewModel.deleteQuery(entity) })
                }
            }
        }
    )
}

@Composable
fun QueryItem(entity: RequestEntity, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entity.queryText, style = MaterialTheme.typography.bodySmall)
            Text(text = entity.timestamp.toString(), style = MaterialTheme.typography.bodyMedium)
            entity.url?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onDelete(entity.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
